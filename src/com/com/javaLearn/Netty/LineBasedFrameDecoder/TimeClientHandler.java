package com.javaLearn.Netty.LineBasedFrameDecoder;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.logging.Logger;

public class TimeClientHandler extends SimpleChannelInboundHandler<String> {

    private static final Logger logger = Logger.getLogger(TimeClientHandler.class.getName());

    private int counter;

    private byte[] req;

    public TimeClientHandler() {
        req = ("QUERY TIME ORDER" + System.getProperty("line.separator")).getBytes();
    }
    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        ByteBuf message = null;
        message = Unpooled.buffer(req.length*100);
        for (int i = 0; i < 100; i++) {

            message.writeBytes(req);

        }
        ctx.writeAndFlush(message);
    }
    @Override
    public void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {

        System.out.println("Now is:" + msg + "; the counter is:" + (++counter));
    }
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        logger.warning("Unexcepted exception from downstream:" + cause.getMessage());
        ctx.close();
    }

}

