package com.framework.netty.tcpLive.server;

import com.framework.netty.tcpLive.TcpLiveMessage;
import com.javaLearn.Netty.Live.ServerEncoder;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.stream.ChunkedWriteHandler;
import io.netty.handler.timeout.IdleStateHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.concurrent.TimeUnit;

@Configuration
@PropertySource("classpath:netty-server-config.properties")
public class TcpLiveServer {

    @Value("${server.tcpLive.port}")
    private int port;

    @Value("${server.tcpLive.channelOption.SO_BACKLOG}")
    private int so_backlog;
    @Value("${server.tcpLive.channelOption.SO_KEEPALIVE}")
    private Boolean so_keepalive;
    @Value("${server.tcpLive.channelOption.SO_SNDBUF}")
    private int so_sndbuf;
    @Value("${server.tcpLive.channelOption.SO_RCVBUF}")
    private int so_rcvbuf;

    @Autowired
    TcpLiveServerHandler tcpLiveServerHandler;

    @Bean
    @Qualifier("tcpLiveServerBootstrap")
    public ServerBootstrap serverBootstrap(){
        ServerBootstrap b = new ServerBootstrap();
        b.group(tcpLiveBossGroup(),tcpLiveWorkerGroup())
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_KEEPALIVE,true)//设置TCP连接，当设置该选项以后，连接会测试链接的状态，这个选项用于可能长时间没有数据交流的连接。当设置该选项以后，如果在两小时内没有数据的通信时，TCP会自动发送一个活动探测数据报文
                .option(ChannelOption.SO_BACKLOG,1024) //服务端处理客户端连接请求是顺序处理的，所以同一时间只能处理一个客户端连接，多个客户端来的时候，服务端将不能处理的客户端连接请求放在队列中等待处理，backlog参数指定了队列的大小
                .option(ChannelOption.SO_SNDBUF,32*1024)//发送缓冲区的大小,发送缓冲区用于保存发送数据，直到发送成功
                .option(ChannelOption.SO_RCVBUF,32*1024)//接收缓冲区的大小，接收缓冲区用于保存网络协议站内收到的数据，直到应用程序读取成功

                .childHandler(channelInitializer());
        return b;
    }
    @Bean
    @Qualifier("tcpLiveBossGroup")
    public NioEventLoopGroup tcpLiveBossGroup() {
        return new NioEventLoopGroup();
    }

    @Bean
    @Qualifier("tcpLiveWorkerGroup")
    public NioEventLoopGroup tcpLiveWorkerGroup() {
        return new NioEventLoopGroup();
    }

    @Bean
    @Qualifier("tcpLiveChannelInitializer")
    public ChannelInitializer channelInitializer(){
        return new ChannelInitializer<SocketChannel>(){

            @Override
            protected void initChannel(SocketChannel ch) throws Exception {
                ByteBuf delimiter = Unpooled.copiedBuffer(TcpLiveMessage.DELIMITER.getBytes());
                ch.pipeline()
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
                        .addLast(tcpLiveServerHandler);

            }
        };
    }

}
