package com.zhangsw.springbootdemo.controller.netty;


import com.zhangsw.springbootdemo.service.netty.WsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/netty/ws-server",method = {RequestMethod.POST,RequestMethod.GET})
public class NettyWs {

    @Autowired
    WsServiceImpl webSocketService;

    @RequestMapping(value = "/bind")
    public String wsServerBind() throws Exception {
        return webSocketService.wsServerBind();
    }

    @RequestMapping(value = "/unbind")
    public String wsServerUnbind(){
        return webSocketService.wsServerUnbind();
    }

    @RequestMapping(value = "/push-message")
    public String serverPush(@RequestParam String msg){
        return webSocketService.serverPushMsg(msg);
    }

}
