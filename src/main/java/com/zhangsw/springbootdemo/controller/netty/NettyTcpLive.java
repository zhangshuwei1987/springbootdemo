package com.zhangsw.springbootdemo.controller.netty;


import com.zhangsw.springbootdemo.service.netty.TcpLiveServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/netty-tcp-live",method = {RequestMethod.POST})
public class NettyTcpLive {

    @Autowired
    TcpLiveServiceImpl tcpLiveService;

    @RequestMapping(value = "/server-start")
    public String webSocketStart(){
        return tcpLiveService.tcpLiveServerBind();

    }

    @RequestMapping(value = "/client-connect")
    public String clientConnect(){
        return null;

    }

    @RequestMapping(value = "/client-send-message")
    public String sendMessage(@RequestParam String message){
        return null;

    }

}
