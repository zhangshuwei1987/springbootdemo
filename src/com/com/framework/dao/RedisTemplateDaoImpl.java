package com.framework.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.concurrent.TimeUnit;

@Repository
public class RedisTemplateDaoImpl {

    @Value("${test.redis.expire}")
    private Long expire;

    @Autowired
    private RedisTemplate redisTemplate;

    public void save(String key,Object value){
        redisTemplate.opsForValue().set(key, value, expire, TimeUnit.MINUTES);
    }
    public Object get(String key){
        return redisTemplate.opsForValue().get(key)==null?"":redisTemplate.opsForValue().get(key);
    }
    public void remove(String key){
        redisTemplate.delete(key);
    }

}
