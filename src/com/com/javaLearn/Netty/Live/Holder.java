package com.javaLearn.Netty.Live;

import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.HashMap;
import java.util.Map;

public class Holder {

    private static final Map<Integer, NioSocketChannel> map = new HashMap<Integer, NioSocketChannel>();

    public static void put(Integer hashCode ,NioSocketChannel channel){
        map.put(hashCode,channel);
    }

    public static void remove(Integer hashCode){
        map.remove(hashCode);
    }

    public static NioSocketChannel get(Integer hashCode) {
        return map.get(hashCode);
    }

    public static Map<Integer, NioSocketChannel> getMAP() {
        return map;
    }


}
