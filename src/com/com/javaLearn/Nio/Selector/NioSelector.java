package com.javaLearn.Nio.Selector;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class NioSelector {

    private Selector selector = null;

    public void initSelector(int port){
        try {

            //开启一个ServerSocketChannel实例（open()实际为工厂方法）
            ServerSocketChannel channel = ServerSocketChannel.open();
            //设置通道为非阻塞
            channel.configureBlocking(false);
            //绑定监听端口号9999
            channel.socket().bind(new InetSocketAddress(port));
            //开启一个选择器实例
            selector = Selector.open();
            //第二个参数代表“兴趣集合”，即Selector监听Channel时可捕获的事件
            //SelectionKey.OP_CONNECT:某个channel成功连接到另一个服务器称为“连接就绪”
            //SelectionKey.OP_ACCEPT:一个server socket channel准备好接收新进入的连接称为“接收就绪”
            //SelectionKey.OP_READ:一个有数据可读的通道可以说是“读就绪”
            //SelectionKey.OP_WRITE:等待写数据的通道可以说是“写就绪”
            //可同时监听多个事件，用“|”串联起来（SelectionKey.OP_READ | SelectionKey.OP_WRITE）
            //此处监听新连接访问
            //将通道注册入选择器，返回包含 interest集合,ready集合,Channel,Selector,附加的对象（可选）的包装类对象
            //将通道注册入选择器,返回包含通道
            SelectionKey selectionKey = channel.register(selector, SelectionKey.OP_ACCEPT);

            selectionKey.interestOps();
            selectionKey.readyOps();
            selectionKey.isAcceptable();
            selectionKey.isConnectable();
            selectionKey.isReadable();
            selectionKey.isWritable();
            selectionKey.channel();
            selectionKey.selector();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void listen() throws IOException {
        while (true){
            //返回你感兴趣的事件已经准备就绪的通道，阻塞到至少有一个通道在你注册的事件上就绪了，此处兴趣事件为ACCEPT(接收就绪)
            int selectCount = selector.select();
            //对兴趣事件准备就绪的通道集合
            Iterator<SelectionKey> itr = selector.selectedKeys().iterator();
            while (itr.hasNext()){
                SelectionKey key = itr.next();
                itr.remove();
                if(key.isConnectable()){
                    // 与远程服务器建立了连接
                }else if(key.isAcceptable()){
                    // 一个连接被ServerSocketChannel接受
                    //服务端通道对象
                    ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key.channel();
                    //返回一个包含新进来的连接的 SocketChannel
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    //设为非阻塞
                    socketChannel.configureBlocking(false);
                    //数据写入客户端通道
                    socketChannel.write(ByteBuffer.wrap(new String("向客户端发送一条消息！").getBytes()));
                    //服务端接收请求后将客户端通道纳入Selector并监听兴趣状态“可读”
                    socketChannel.register(selector,SelectionKey.OP_READ);
                }else if(key.isWritable()){
                    // 一个channel做好了写准备
                }else if(key.isReadable()){
                    // 一个channel做好了读准备
                   SocketChannel socketChannel = (SocketChannel)key.channel();
                    // 创建读取的缓冲区
                    ByteBuffer buffer = ByteBuffer.allocate(50);
                    socketChannel.read(buffer);
                    byte[] data = buffer.array();
                    String msg = new String(data).trim();
                    System.out.println("服务端收到信息："+msg);
                    ByteBuffer outBuffer = ByteBuffer.wrap(msg.getBytes());
                    socketChannel.write(outBuffer);// 将消息回送给客户端

                }

            }

        }
    }




    public static void main(String[] args) throws IOException {
        NioSelector nioSelector = new NioSelector();
        nioSelector.initSelector(8888);
        nioSelector.listen();
    }
}
