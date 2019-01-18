package com.javaLearn.Netty.Encoder;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

//MessageToByteEncoder<T>:将消息实体T从对象转换成byte，写入到ByteBuf，然后再丢给剩下的ChannelOutboundHandler传给客户端
public class StringToByteEncoder extends MessageToByteEncoder<String> {


    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, String s, ByteBuf out) throws Exception {
        out.writeBytes(s.getBytes());

    }
}
