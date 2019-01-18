package com.javaLearn.Netty.NettyServerClient;

import com.sun.org.apache.xpath.internal.operations.String;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;

import java.nio.charset.Charset;

public class NettyServer {

    public static void main(String[] args) throws InterruptedException {

        //NioServerSocketChannel轮询组
        NioEventLoopGroup boss = new NioEventLoopGroup();
        //NioSocketChannel轮询组
        NioEventLoopGroup worker = new NioEventLoopGroup(8);
        try {
            //server配置类
            ServerBootstrap serverBootstrap = new ServerBootstrap();

            serverBootstrap
                    .group(boss, worker)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<NioSocketChannel>() {
                        protected void initChannel(NioSocketChannel ch) {
                            //pipeline：channel的Handler执行链
                            //StringDecoder：继承了ChannelInboundHandlerAdapter 对入站字节数据解码（以当前系统的编码集）
                            ch.pipeline().addLast(new StringDecoder());
                            ch.pipeline().addLast(new SimpleChannelInboundHandler<ByteBuf>() {
                                @Override
                                protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) {
                                    byte[] bytes = new byte[msg.readableBytes()];
                                    msg.readBytes(bytes);
                                    System.out.println(new java.lang.String(bytes,Charset.forName("UTF-8")));
                                }
                            });
                        }
                    });

            ChannelFuture channelFuture = serverBootstrap.bind(8000).sync();
            channelFuture.channel().closeFuture().sync();
        }finally {
            boss.shutdownGracefully();
            worker.shutdownGracefully();
        }
    }



}
