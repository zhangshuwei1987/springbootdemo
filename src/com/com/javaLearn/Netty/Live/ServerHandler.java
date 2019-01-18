package com.javaLearn.Netty.Live;

import com.alibaba.fastjson.JSON;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.timeout.IdleStateEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.Charset;

public class ServerHandler extends SimpleChannelInboundHandler<String> {

    private final static Logger LOGGER = LoggerFactory.getLogger(ServerHandler.class);

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
    }

    //当建立一个新的连接时调用 ChannelActive()
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("Client"+ctx.channel().remoteAddress()+" connected");
        Holder.put(ctx.channel().hashCode(),(NioSocketChannel) ctx.channel());
    }

    //在出现超时事件时会被触发，包括读空闲超时或者写空闲超时
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if(evt instanceof IdleStateEvent){
            IdleStateEvent event = (IdleStateEvent)evt;
            LOGGER.info("server:channel-read-idle-with-10-seconds！");
            ctx.writeAndFlush(new Protocol(Protocol.TYPE_HEART,"pong"));
        }

        super.userEventTriggered(ctx, evt);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg)  {
        try{

            Protocol customProtocol = JSON.parseObject(msg, Protocol.class);
            switch (customProtocol.getType()){
                case Protocol.TYPE_HEART:

                    break;
                case Protocol.TYPE_CONTENT:
                    LOGGER.info("receive-client-content"+ msg);
                    ctx.writeAndFlush(customProtocol);
                    break;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }



    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
        cause.printStackTrace();
        ctx.close();

    }

}
