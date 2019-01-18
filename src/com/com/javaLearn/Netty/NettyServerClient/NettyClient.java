package com.javaLearn.Netty.NettyServerClient;

import com.javaLearn.Netty.Encoder.StringToByteEncoder;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.Date;

public class NettyClient {

    public static void main(String[] args) throws InterruptedException {
        //client-socket辅助配置类
        Bootstrap bootstrap = new Bootstrap();

        NioEventLoopGroup group = new NioEventLoopGroup();

        bootstrap.group(group)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<Channel>() {
                    @Override
                    protected void initChannel(Channel ch) {
                        //StringEncoder：继承了ChannelOutboundHandlerAdapter 对出站字符串数据编码为字节数据（以当前系统的编码集）
                        ch.pipeline().addLast(new StringToByteEncoder());
                    }
                });

        Channel channel = bootstrap.connect("127.0.0.1", 8000).channel();
        //每隔两秒对服务端发送数据
        while (true) {
            String abc = "";
            int i = 0;
            while (i<1000){
                abc += "你好你好你好";
                i++;
                channel.writeAndFlush(abc);
                Thread.sleep(2000);
            }


        }
    }


}
