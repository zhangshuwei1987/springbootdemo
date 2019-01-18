package com.framework.redis.example.controller;

import com.alibaba.fastjson.JSON;
import com.framework.redis.example.service.RedisCacheServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/redis-cache",method = {RequestMethod.POST})
public class RedisCache {

    @Autowired
    RedisCacheServiceImpl redisCacheService;

    @RequestMapping(value = "/find-by-id")
    public String findById(@RequestParam Integer id){

        return JSON.toJSONString(redisCacheService.findById(id));
    }

}
