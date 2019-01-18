package com.javaLearn.Nio.transfer;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

//通道间的数据传输
public class Transfer {

    public static void transferFrom(){
        RandomAccessFile fromFile = null;
        RandomAccessFile toFile = null;
        try {
            fromFile = new RandomAccessFile("E:\\ideaWorkSpace\\springbootdemo\\src\\com\\com\\javaLearn\\Nio\\transfer\\transferFrom\\from.txt","rw");
            FileChannel fromChannel = fromFile.getChannel();
            toFile = new RandomAccessFile("E:\\ideaWorkSpace\\springbootdemo\\src\\com\\com\\javaLearn\\Nio\\transfer\\transferFrom\\to.txt","rw");
            FileChannel toChannel = toFile.getChannel();
            long position = 0;
            long count = fromChannel.size();
            toChannel.transferFrom(fromChannel,position,count);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void transferTo(){
        RandomAccessFile fromFile = null;
        RandomAccessFile toFile = null;
        try {
            fromFile = new RandomAccessFile("E:\\ideaWorkSpace\\springbootdemo\\src\\com\\com\\javaLearn\\Nio\\transfer\\transferTo\\from.txt","rw");
            FileChannel fromChannel = fromFile.getChannel();
            toFile = new RandomAccessFile("E:\\ideaWorkSpace\\springbootdemo\\src\\com\\com\\javaLearn\\Nio\\transfer\\transferTo\\to.txt","rw");
            FileChannel toChannel = toFile.getChannel();
            long position = 0;
            long count = fromChannel.size();
            fromChannel.transferTo(position,count,toChannel);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Transfer.transferFrom();
        Transfer.transferTo();
    }

}
