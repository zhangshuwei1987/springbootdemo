package com.framework.netty.tcpLive.client;

import com.framework.netty.tcpLive.TcpLiveMessage;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.timeout.IdleStateHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class TcpLiveClient {

    private final static Logger LOGGER = LoggerFactory.getLogger(TcpLiveClient.class);

    private static ChannelFuture channelFuture = null;

    @Autowired
    TcpLiveClientHandler tcpLiveClientHandler;

    @Bean
    @Qualifier("tcpLiveClientGroup")
    public NioEventLoopGroup tcpLiveClientGroup() {
        return new NioEventLoopGroup();
    }

    @Bean
    public Bootstrap bootstrap(){
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(tcpLiveClientGroup())
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel socketChannel) throws Exception {
                        ByteBuf splitBuf = Unpooled.copiedBuffer(TcpLiveMessage.DELIMITER.getBytes());
                        socketChannel.pipeline()
                                .addLast(new DelimiterBasedFrameDecoder(1024,splitBuf))
                                .addLast(new StringDecoder())
                                .addLast(new TcpLiveClientEncoder())

                                .addLast(new IdleStateHandler(0,10,0))
                                .addLast(tcpLiveClientHandler);
                    }
                });
        return bootstrap;
    }


}
