package com.framework.netty.tcpLive.client;

import com.alibaba.fastjson.JSON;
import com.framework.netty.tcpLive.TcpLiveMessage;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.nio.charset.Charset;
import java.util.List;

public class TcpLiveClientDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf in, List<Object> out) throws Exception {
        byte[] bytes = new byte[in.readableBytes()] ;
        in.readBytes(bytes) ;
        TcpLiveMessage message = JSON.parseObject(new String(bytes,Charset.forName("UTF-8")), TcpLiveMessage.class);
        out.add(message);
    }
}
