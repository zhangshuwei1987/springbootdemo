package com.zhangsw.springbootdemo.service.netty;

import com.framework.exception.ZswException;
import com.framework.netty.tcpLive.client.TcpLiveClient;
import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.nio.NioEventLoopGroup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Transactional(rollbackFor = Exception.class)
@PropertySource("classpath:netty-server-config.properties")
public class TcpLiveServiceImpl {

    private final static Logger LOGGER = LoggerFactory.getLogger(TcpLiveServiceImpl.class);

    @Resource
    ServerBootstrap tcpLiveServerBootstrap;

    @Resource
    NioEventLoopGroup tcpLiveBossGroup;

    @Resource
    NioEventLoopGroup tcpLiveWorkerGroup;

    @Value("${server.tcpLive.port}")
    private int tcpLivePort;

    public String tcpLiveServerBind(){

        try {
            ChannelFuture channelFuture = tcpLiveServerBootstrap.bind(tcpLivePort).sync();
            channelFuture.channel().closeFuture().addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture channelFuture) throws Exception {
                    tcpLiveBossGroup.shutdownGracefully();
                    tcpLiveWorkerGroup.shutdownGracefully();
                }
            });
        } catch (InterruptedException e) {
            throw new ZswException("99","start-has-error!");

        }
        return "start-success";
    }

    @Autowired
    Bootstrap bootstrap;

    public String tcpLiveClientConnect(){
        try {
            ChannelFuture channelFuture = bootstrap.connect("localhost",tcpLivePort).sync();
            if (channelFuture.isSuccess()) {
                LOGGER.info("连接服务端成功");
            }
            channelFuture.channel().closeFuture().addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture channelFuture) throws Exception {
                    tcpLiveBossGroup.shutdownGracefully();
                    tcpLiveWorkerGroup.shutdownGracefully();
                }
            });
        } catch (InterruptedException e) {
            throw new ZswException("99","start-has-error!");

        }
        return "connect-success";
    }

}
