package com.framework.netty.wsServer;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WsChannelInitializer extends ChannelInitializer<SocketChannel> {

    @Autowired
    WsHandler wsHandler;

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ch.pipeline()
                //HttpRequestDecoder及HttpResponseEncoder的结合体
                //将字节解码为HttpRequest，HttpContent和LastHttpContent，并将HttpRequest，HttpContent和LastHttpContent编码为字节
                .addLast(new HttpServerCodec())
                //netty提供的支持大文件数据写的一个处理器
                .addLast(new ChunkedWriteHandler())
                //HTTP REQUEST/RESPONSE消息聚合器。
                // 将分段(即多次请求)的HTTP request/respone(RequestHeader/ResponseHeader、HttpContent、LastHttpContent)聚合为一个FullHttpRequest/FullHttpResponse                        .addLast(new HttpObjectAggregator(512*1024))
                .addLast(new HttpObjectAggregator(512*1024))
                //它负责websocket握手以及处理控制框架（Close，Ping（心跳检检测request），Pong（心跳检测响应））。
                // 文本和二进制数据帧被传递到管道中的下一个处理程序（由您实现）进行处理。
                //只能处理WebScoket服务，不能处理其他http服务
                .addLast(new WebSocketServerProtocolHandler("/myWs"))
                .addLast(wsHandler);
    }
}
