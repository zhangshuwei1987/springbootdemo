package com.javaLearn.Netty.Live;

import com.alibaba.fastjson.JSON;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.nio.charset.Charset;
import java.util.List;

public class ClientDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf in, List<Object> out) throws Exception {
        byte[] bytes = new byte[in.readableBytes()] ;
        in.readBytes(bytes) ;
        Protocol customProtocol = JSON.parseObject(new String(bytes,Charset.forName("UTF-8")), Protocol.class);
        out.add(customProtocol);
    }
}
