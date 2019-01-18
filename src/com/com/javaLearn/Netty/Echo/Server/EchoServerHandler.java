package com.javaLearn.Netty.Echo.Server;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

//标识这类的实例之间可以在 channel 里面共享
//复写ChannelInboundHandlerAdapter生命周期的钩子函数
@ChannelHandler.Sharable
public class EchoServerHandler extends ChannelInboundHandlerAdapter {
    //当建立一个新的连接时调用 ChannelActive()
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("Client"+ctx.channel().remoteAddress()+" connected");
    }

    //批处理中的单条信息读取
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //入站信息
        ByteBuf in = (ByteBuf) msg;
        System.out.println("Server received: " + in.toString(CharsetUtil.UTF_8));
        ctx.write(in);//将所接收的消息返回给发送者。注意，这还没有冲刷数据（尚未最终提交）
    }
    //当前批处理中的最后一条消息时调用
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush(Unpooled.EMPTY_BUFFER)//冲刷所有待审消息到远程节点，返回ChannelFuture对象
                .addListener(ChannelFutureListener.CLOSE);//添加通道关闭的状态监听
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace(); //打印异常堆栈跟踪
        ctx.close();//关闭通道
    }
}
