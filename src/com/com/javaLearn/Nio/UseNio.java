package com.javaLearn.Nio;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class UseNio {

    public static void use(){
        RandomAccessFile aFile = null;
        try {
            aFile = new RandomAccessFile("D:\\receiveDoc.txt","rw");
            FileChannel inChannel = aFile.getChannel();
            //分配48字节的BYTEBUFFER
            ByteBuffer buf = ByteBuffer.allocate(48);
            int byteRead = inChannel.read(buf);
            while (byteRead != -1){
                buf.flip();//切换ByteBuffer为读模式
                while (buf.hasRemaining()){
                    System.out.println((char) buf.get()); // 每次读取1byte，也就是一个字节
                }
                buf.clear();
                byteRead = inChannel.read(buf);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        UseNio.use();
    }

}
