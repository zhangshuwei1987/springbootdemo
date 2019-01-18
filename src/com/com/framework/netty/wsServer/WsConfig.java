package com.framework.netty.wsServer;

import io.netty.channel.Channel;
import io.netty.channel.nio.NioEventLoopGroup;

import java.util.HashMap;
import java.util.Map;

public class WsConfig {

    private static final Map<String,Channel> group = new HashMap<String,Channel>();

    private static Channel SERVER_CHANNEL = null;

    private static NioEventLoopGroup bossGroup;

    private static NioEventLoopGroup workerGroup;

    public static void put(String id ,Channel ch){
        group.put(id,ch);
    }

    public static void remove(String id){
        group.remove(id);
    }

    public static void removeAll(){
        group.clear();
    }

    public static Map<String,Channel> get(){
        return group;
    }


    public static Channel getServerChannel() {
        return SERVER_CHANNEL;
    }

    public static void setServerChannel(Channel serverChannel) {
        SERVER_CHANNEL = serverChannel;
    }

    public static NioEventLoopGroup getBossGroup() {
        return bossGroup;
    }

    public static void setBossGroup(NioEventLoopGroup bossGroup) {
        WsConfig.bossGroup = bossGroup;
    }

    public static NioEventLoopGroup getWorkerGroup() {
        return workerGroup;
    }

    public static void setWorkerGroup(NioEventLoopGroup workerGroup) {
        WsConfig.workerGroup = workerGroup;
    }
}
