package com.javaLearn.Netty.Live;

import java.io.Serializable;

public class Protocol implements Serializable {
    public static final String TYPE_HEART = "HEART_BEAT";
    public static final String TYPE_CONTENT = "CONTENT";
    private static final long serialVersionUID = 4671171056588401542L;
    private String type;
    private String content ;

    public Protocol(){

    }

    public Protocol(String type, String content){
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


