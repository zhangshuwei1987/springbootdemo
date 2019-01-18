package com.framework.controller;

import com.alibaba.fastjson.JSON;
import com.framework.core.ZswSpringApplicationContext;
import com.framework.core.context.ApplicationContextProvider;
import com.springLearn.propertiesRelate.TestMainProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value="/spring-base",method = {RequestMethod.POST})
public class SpringBaseController{

    @Autowired
    TestMainProperties testMainProperties;

    @RequestMapping(value = "/get-bean")
    public Object getSpringBean(@RequestParam(required = false) String beanName) throws ClassNotFoundException {
        Object instanceProxy =  ApplicationContextProvider.<Object>getBean(beanName);

        Object instanceTarget = ZswSpringApplicationContext.getSourceBeanFromSpringContextByBeanName(beanName);

        Map<String,String> objMap = new HashMap<>();
        objMap.put("proxyOrOriginalInstance",instanceProxy.getClass().getName()+"@"+instanceProxy.hashCode());
        objMap.put("targetInstance",instanceTarget.getClass().getName()+"@"+instanceTarget.hashCode());

        return JSON.toJSONString(objMap);
    }


}
