package com.javaLearn.Netty.Live;

import com.alibaba.fastjson.JSON;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

import java.nio.charset.Charset;

//出站编码器
public class ClientEncoder extends MessageToByteEncoder<Protocol> {
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Protocol msg, ByteBuf out) throws Exception {


        out.writeBytes(Unpooled.copiedBuffer((JSON.toJSONString(msg)+"$_").getBytes()));

    }
}
