package com.framework.netty.wsServer;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/*
* WebSocket规范中定义了6种类型的桢，netty为其提供了具体的对应的POJO实现。
* 具体POJO：BinaryWebSocketFrame, CloseWebSocketFrame, ContinuationWebSocketFrame, PingWebSocketFrame, PongWebSocketFrame, TextWebSocketFrame
* WebSocketFrame：所有桢的父类，所谓桢就是WebSocket服务在建立的时候，在通道中处理的数据类型。本列子中客户端和服务器之间处理的是文本信息。所以范型参数是TextWebSocketFrame。
* */
@Component
//Bean默认为单例模式，在NETTY-channel-pipeline流水线中加入的Handler必须为原型模式（NEW Handler（）），故当前Handler-bean想要为所有Channel公用，必须设为@Shareable
@ChannelHandler.Sharable
public class WsHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

    private final static Logger LOGGER = LoggerFactory.getLogger(WsHandler.class);

    // 当通道建立时
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        String channelId = ctx.channel().id().asLongText();

        LOGGER.info("id:"+channelId+" connect success");
        WsConfig.get().entrySet().stream().forEach(entry -> {
            /*if(!entry.getKey().equals(ctx.channel().id().asLongText())){

            }*/
            Channel ch = (Channel)entry.getValue();
            ch.writeAndFlush(new TextWebSocketFrame(channelId+"加入！"));
        });
        WsConfig.put(channelId,ctx.channel());

    }
    // 当通道关闭时
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
        String channelId = ctx.channel().id().asLongText();
        WsConfig.remove(channelId);
        WsConfig.get().entrySet().stream().forEach(entry -> {

            Channel ch = (Channel)entry.getValue();
            ch.writeAndFlush(new TextWebSocketFrame(channelId+"离开！"));
        });
        LOGGER.info("id:"+channelId+" disconnect success");
    }

    // 有可读数据时
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
        String text = msg.text();
        LOGGER.info("receive-text:"+text);
        WsConfig.get().entrySet().stream().forEach(entry -> {
            if(!entry.getKey().equals(ctx.channel().id().asLongText())){
                Channel ch = (Channel)entry.getValue();
                ch.writeAndFlush(new TextWebSocketFrame(text));
            }

        });

    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        super.channelReadComplete(ctx);
        //冲刷SEND缓存区数据
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
        if(ctx!=null)ctx.close();
        if(ctx!=null)cause.printStackTrace();
    }


}
