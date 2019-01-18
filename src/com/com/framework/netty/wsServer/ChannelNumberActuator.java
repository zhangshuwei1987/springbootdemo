package com.framework.netty.wsServer;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.WriteOperation;
import org.springframework.stereotype.Component;

@Endpoint(id = "nettyWebSocket-client-channel-number")
@Component
public class ChannelNumberActuator {
    //@ReadOperation:对应http-request-method GET
    @ReadOperation
    public String doGet(){

        return String.valueOf(WsConfig.get().size());
    }

    @WriteOperation
    public String doPost(){
        return String.valueOf(WsConfig.get().size());
    }

}
