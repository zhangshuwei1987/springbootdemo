package com.framework.netty.tcpLive.server;

import com.alibaba.fastjson.JSON;
import com.framework.netty.tcpLive.TcpLiveMessage;
import com.javaLearn.Netty.Live.Protocol;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

//出站编码器
public class TcpLiveServerEncoder extends MessageToByteEncoder<Protocol> {
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Protocol msg, ByteBuf out) throws Exception {
        out.writeBytes(Unpooled.copiedBuffer((JSON.toJSONString(msg).getBytes()+ TcpLiveMessage.DELIMITER).getBytes()));
    }
}
