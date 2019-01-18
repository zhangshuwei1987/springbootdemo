package com.framework.netty.tcpLive.client;

import com.alibaba.fastjson.JSON;
import com.framework.netty.tcpLive.TcpLiveMessage;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

//出站编码器
public class TcpLiveClientEncoder extends MessageToByteEncoder<TcpLiveMessage> {
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, TcpLiveMessage msg, ByteBuf out) throws Exception {


        out.writeBytes(Unpooled.copiedBuffer((JSON.toJSONString(msg)+ TcpLiveMessage.DELIMITER).getBytes()));

    }
}
