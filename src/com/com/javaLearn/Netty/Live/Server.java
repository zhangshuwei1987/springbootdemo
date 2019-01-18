package com.javaLearn.Netty.Live;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

public class Server {

    public void start(){
        //NioServerSocketChannel绑定指定端口并用于接收新连接
        //Selector轮询检查新连接Accepted状态，将新连接包装为NioSocketChannel对象交予worker-selector
        NioEventLoopGroup boss = new NioEventLoopGroup();
        //轮询boss写入的channel对象的状态（Read&Write）,满足可读或可写状态的Channel将会执行其pipeline（Encoder、Decoder、Handler）
        NioEventLoopGroup worker = new NioEventLoopGroup();
        //服务辅助类
        ServerBootstrap serverBootstrap = new ServerBootstrap();

        try {
        serverBootstrap.group(boss,worker)
                .channel(NioServerSocketChannel.class)//设置NIO模式
                /*
                * ChannelOption:http://www.cnblogs.com/rwxwsblog/p/7762094.html
                * */
                .option(ChannelOption.SO_KEEPALIVE,true)//设置TCP连接，当设置该选项以后，连接会测试链接的状态，这个选项用于可能长时间没有数据交流的连接。当设置该选项以后，如果在两小时内没有数据的通信时，TCP会自动发送一个活动探测数据报文
                .option(ChannelOption.SO_BACKLOG,1024) //服务端处理客户端连接请求是顺序处理的，所以同一时间只能处理一个客户端连接，多个客户端来的时候，服务端将不能处理的客户端连接请求放在队列中等待处理，backlog参数指定了队列的大小
                .option(ChannelOption.SO_SNDBUF,32*1024)//发送缓冲区的大小,发送缓冲区用于保存发送数据，直到发送成功
                .option(ChannelOption.SO_RCVBUF,32*1024)//接收缓冲区的大小，接收缓冲区用于保存网络协议站内收到的数据，直到应用程序读取成功
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel socketChannel) throws Exception {
                        ByteBuf delimiter = Unpooled.copiedBuffer("$_".getBytes());
                        socketChannel.pipeline()
                                //DelimiterBasedFrameDecoder:自定义符号分包解码器
                                //maxFrameLength:DelimiterBasedFrameDecoder的最大输入字节长度
                                //delimiter:自定义分隔符，必须包装为ByteBuf
                                .addLast(new DelimiterBasedFrameDecoder(1024,delimiter))
                                .addLast(new StringDecoder())
                                .addLast(new ServerEncoder())
                                //心跳监测控制器：
                                // （客户端到服务器之间的通信往往需要穿越多个中间节点，例如路由器、网关、防火墙等，
                                // 大部分防火墙默认会关闭长时间处于非活跃状态的连接而导致 Socket 连接断连，因此需要通过轮询告诉网络，该连接处于活跃状态，KEEP-ALIVE的默认轮询时间为2小时，过长）
                                //readerIdleTime:读空闲满足条件触发IdleStateHandler实例
                                //writerIdleTime:写空闲满足条件触发IdleStateHandler实例
                                //allIdleTime:满足上述其一条件即触发IdleStateHandler实例
                                .addLast(new IdleStateHandler(10,0,0, TimeUnit.SECONDS))
                                .addLast(new ServerHandler());

                    }
                });
        ChannelFuture channelFuture = serverBootstrap.bind(8888).sync();

            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            boss.shutdownGracefully();
            worker.shutdownGracefully();
        }

    }

    public static void main(String[] args) {
        new Server().start();
    }

}
