package com.zhangsw.springbootdemo.service.netty;

import com.framework.exception.ZswException;
import com.framework.netty.wsServer.WsChannelInitializer;
import com.framework.netty.wsServer.WsConfig;
import com.framework.netty.wsServer.WsProperties;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
/*
 * WEBSOCKET测试地址：http://www.blue-zero.com/WebSocket/
 * */
@Service
@Transactional(rollbackFor = Exception.class)
@PropertySource("classpath:netty-server-config.properties")
public class WsServiceImpl {



    @Autowired
    WsChannelInitializer channelInitializer;

    @Autowired
    WsProperties wsProperties;

    public String wsServerBind() throws Exception{

            NioEventLoopGroup bossGroup = new NioEventLoopGroup();
            NioEventLoopGroup workerGroup = new NioEventLoopGroup();
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup,workerGroup)
                    .option(ChannelOption.SO_RCVBUF,wsProperties.getSo_rcvbuf())//接收缓冲区的大小，接收缓冲区用于保存网络协议站内收到的数据，直到应用程序读取成功
                    .option(ChannelOption.SO_SNDBUF,wsProperties.getSo_sndbuf())//发送缓冲区的大小,发送缓冲区用于保存发送数据，直到发送成功
                    .option(ChannelOption.SO_BACKLOG,wsProperties.getSo_backlog())//服务端处理客户端连接请求是顺序处理的，所以同一时间只能处理一个客户端连接，多个客户端来的时候，服务端将不能处理的客户端连接请求放在队列中等待处理，backlog参数指定了队列的大小
                    .channel(NioServerSocketChannel.class)
                    .childHandler(channelInitializer);
            //阻塞直到绑定成功
            ChannelFuture channelFuture = b.bind(wsProperties.getPort()).sync();
            //服务绑定成功后将服务端相关对象存入内存备用
            WsConfig.setServerChannel(channelFuture.channel());
            WsConfig.setBossGroup(bossGroup);
            WsConfig.setWorkerGroup(workerGroup);
            channelFuture.channel().closeFuture().addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture channelFuture) throws Exception {
                    bossGroup.shutdownGracefully();
                    workerGroup.shutdownGracefully();
                }
            });

        return "bind-success";
    }

    public String wsServerUnbind(){
        Channel serverCh = WsConfig.getServerChannel();
        serverCh.close();
        WsConfig.setBossGroup(null);
        WsConfig.setWorkerGroup(null);
        return "unbind-success";
    }

    public String serverPushMsg(String msg){
        StringBuilder sb = new StringBuilder();
        sb.append("客服消息：");
        sb.append(msg);
        String message = sb.toString();
        WsConfig.get().entrySet().stream().forEach(entry ->{
            Channel ch = (Channel)entry.getValue();
            ch.writeAndFlush(new TextWebSocketFrame(message));
        });

        return "push-success";
    }

}
