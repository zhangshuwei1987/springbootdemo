package com.framework.netty.wsServer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:netty-server-config.properties")
public class WsProperties {

    @Value("${server.ws.port}")
    private int port;

    @Value("${server.ws.channelOption.SO_BACKLOG}")
    private int so_backlog;
    @Value("${server.ws.channelOption.SO_KEEPALIVE}")
    private Boolean so_keepalive;
    @Value("${server.ws.channelOption.SO_SNDBUF}")
    private int so_sndbuf;
    @Value("${server.ws.channelOption.SO_RCVBUF}")
    private int so_rcvbuf;

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getSo_backlog() {
        return so_backlog;
    }

    public void setSo_backlog(int so_backlog) {
        this.so_backlog = so_backlog;
    }

    public Boolean getSo_keepalive() {
        return so_keepalive;
    }

    public void setSo_keepalive(Boolean so_keepalive) {
        this.so_keepalive = so_keepalive;
    }

    public int getSo_sndbuf() {
        return so_sndbuf;
    }

    public void setSo_sndbuf(int so_sndbuf) {
        this.so_sndbuf = so_sndbuf;
    }

    public int getSo_rcvbuf() {
        return so_rcvbuf;
    }

    public void setSo_rcvbuf(int so_rcvbuf) {
        this.so_rcvbuf = so_rcvbuf;
    }
}
