package com.javaLearn.Netty.HttpServer;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;

public class HttpServer {

    public void start() throws Exception {
        //接收连接
        NioEventLoopGroup boss = new NioEventLoopGroup();
        //处理客户端CHANNEL
        NioEventLoopGroup worker = new NioEventLoopGroup();

        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(boss,worker)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {

                    @Override
                    protected void initChannel(NioSocketChannel socketChannel) throws Exception {
                        socketChannel.pipeline()
                                .addLast(new HttpRequestDecoder())//HTTP-REQUEST解码器
                                .addLast(new HttpResponseEncoder())//HTTP-REPONSE编码器
                                //HTTP REQUEST/RESPONSE消息聚合器。将分段(即多次请求)的HTTP request/respone(RequestHeader/ResponseHeader、HttpContent、LastHttpContent)聚合为一个FullHttpRequest/FullHttpResponse
                                .addLast(new HttpObjectAggregator(512*1024))
                                .addLast(new HttpHandler());
                    }
                })
                //ChannelOption.SO_BACKLOG对应的是tcp/ip协议listen函数中的backlog参数。函数listen(int socketfd, int backlog)用来初始化服务端可连接队列。
                //服务端处理客户端连接请求是顺序处理的，所以同一时间只能处理一个客户端连接，多个客户端来的时候，服务端将不能处理的客户端连接请求放在队列中等待处理，backlog参数指定了队列的大小。
                .option(ChannelOption.SO_BACKLOG, 128) // determining the number of connections queued
                //Channeloption.SO_KEEPALIVE参数对应于套接字选项中的SO_KEEPALIVE，该参数用于设置TCP连接，当设置该选项以后，连接会测试链接的状态，这个选项用于可能长时间没有数据交流的连接。
                //当设置该选项以后，如果在两小时内没有数据的通信时，TCP会自动发送一个活动探测数据报文。
                .childOption(ChannelOption.SO_KEEPALIVE, Boolean.TRUE);


        serverBootstrap.bind(8888).sync();
    }

    public static void main(String[] args) {
        try {
            new HttpServer().start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
