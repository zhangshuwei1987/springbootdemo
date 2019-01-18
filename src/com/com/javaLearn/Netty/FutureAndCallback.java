package com.javaLearn.Netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.socket.ServerSocketChannel;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.SocketChannelConfig;
import io.netty.util.Attribute;
import io.netty.util.AttributeKey;

import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.charset.Charset;

public class FutureAndCallback {


    public void connect(){
        Channel channel = null;
        //异步连接到远程对等节点,返回异步操作状态对象，添加Listener可监听指定事件
        ChannelFuture channelFuture = channel.connect(new InetSocketAddress("192.168.0.1", 25));
        channelFuture.addListener(new ChannelFutureListener() {
            //当operationComplete完成时（当前指连接操作完成）检查状态
            @Override
            public void operationComplete(ChannelFuture channelFuture) throws Exception {
                if (channelFuture.isSuccess()){
                    ByteBuf buffer = Unpooled.copiedBuffer(
                            "Hello", Charset.defaultCharset()); //如果成功就创建一个 ByteBuf 来保存数据
                    ChannelFuture wf = channelFuture.channel().writeAndFlush(buffer);                //异步发送数据到远程。再次返回ChannelFuture
                } else {
                    Throwable cause = channelFuture.cause();        //如果有一个错误则抛出 Throwable,描述错误原因
                    cause.printStackTrace();
                }
            }
        });
    }

    public static void main(String[] args) {

    }
}
