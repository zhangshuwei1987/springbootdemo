package com.javaLearn.Netty.NettyServerClient;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class MessageEncoder extends MessageToByteEncoder<RequestInfoVo> {
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, RequestInfoVo msg, ByteBuf byteBuf) throws Exception {

    }
}
