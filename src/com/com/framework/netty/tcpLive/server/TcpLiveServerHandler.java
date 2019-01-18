package com.framework.netty.tcpLive.server;

import com.alibaba.fastjson.JSON;
import com.framework.netty.tcpLive.TcpLiveMessage;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleStateEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@ChannelHandler.Sharable
public class TcpLiveServerHandler extends SimpleChannelInboundHandler<String> {

    private final static Logger LOGGER = LoggerFactory.getLogger(TcpLiveServerHandler.class);


    //在出现超时事件时会被触发，包括读空闲超时或者写空闲超时（当前设为读取空闲超时）
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if(evt instanceof IdleStateEvent){
            IdleStateEvent event = (IdleStateEvent)evt;
            LOGGER.info("server:channel-read-idle-with-10-seconds！");
            ctx.writeAndFlush(new TcpLiveMessage(TcpLiveMessage.TYPE_HEART,"pong"));
        }

        super.userEventTriggered(ctx, evt);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg)  {
        try{

            TcpLiveMessage tcpLiveMessage = JSON.parseObject(msg, TcpLiveMessage.class);
            switch (tcpLiveMessage.getType()){
                case TcpLiveMessage.TYPE_HEART:

                    break;
                case TcpLiveMessage.TYPE_CONTENT:
                    LOGGER.info("receive-client-content"+ msg);
                    ctx.writeAndFlush(tcpLiveMessage);
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
