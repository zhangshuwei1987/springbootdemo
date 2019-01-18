package com.javaLearn.Netty.Live;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.timeout.IdleStateHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Client {

    private final static Logger LOGGER = LoggerFactory.getLogger(Client.class);

    private static ChannelFuture channelFuture = null;

    public void connect(){
        EventLoopGroup group = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        try {
        bootstrap.group(group)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel socketChannel) throws Exception {
                        ByteBuf splitBuf = Unpooled.copiedBuffer("$_".getBytes());
                        socketChannel.pipeline()
                                .addLast(new DelimiterBasedFrameDecoder(1024,splitBuf))
                                .addLast(new StringDecoder())
                                .addLast(new ClientEncoder())

                                .addLast(new IdleStateHandler(0,10,0))
                                .addLast(new ClientHandler());
                    }
                });

            channelFuture = bootstrap.connect("localhost",8888).sync();
            if (channelFuture.isSuccess()) {
                LOGGER.info("启动 Netty 成功");
            }


           new Thread(new Runnable() {
                @Override
                public void run() {
                    int count = 0;
                    while (count<10000){
                        try {
                            LOGGER.info("single-thread-send-content-"+count);
                            Protocol protocol = new Protocol();
                            protocol.setType(Protocol.TYPE_CONTENT);
                            protocol.setContent("content-message-"+count);
                            channelFuture.channel().writeAndFlush(protocol);
                            count++;
                            Thread.sleep(3500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();

            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            group.shutdownGracefully();
        }
    }

    public static void main(String[] args) {
        Client client = new Client();
        client.connect();




    }

}
