package com.javaLearn.Netty.NettyServerClient;

//消息类
public class RequestInfoVo {

    private String body;

    private int  Type;
    private int  Sequence;

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public int getType() {
        return Type;
    }

    public void setType(int type) {
        Type = type;
    }

    public int getSequence() {
        return Sequence;
    }

    public void setSequence(int sequence) {
        Sequence = sequence;
    }
}
