package com.framework.netty.tcpLive;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

public class TcpLiveMessage {

    public static final String TYPE_HEART = "HEART_BEAT";
    public static final String TYPE_CONTENT = "CONTENT";
    private static final long serialVersionUID = 4671171056588401542L;
    private String type;
    private String content ;

    public static final String DELIMITER = "$_";

    public TcpLiveMessage(){

    }

    public TcpLiveMessage(String type, String content){
        this.type = type;
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
