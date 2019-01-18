package com.framework.redis.example.service;

import com.framework.service.BasServiceImpl;
import com.zhangsw.springbootdemo.entity.TblStaffInfo;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class RedisCacheServiceImpl extends BasServiceImpl {

    public static final String CACHE_NAME = "redis_staff_info_cache";

    /*
    * cacheNames: 指定缓存MAP的名称
    *   key：自定义缓存KEY,缓存的key也可以指定对象的成员变量,例如(#user.id)
    *   cacheManager:指定缓存管理器，默认是加载返回实现了CacheMananer接口的类实例对象的Bean
    * */
    @Cacheable(cacheNames = RedisCacheServiceImpl.CACHE_NAME,key = "#id",cacheManager = "cacheManager")
    public TblStaffInfo findById(Integer id){
       return (TblStaffInfo)this.jpaBasDao.get(TblStaffInfo.class,id);
    }
    //remove cache with key:#id where do remove operation
    @CacheEvict(cacheNames = RedisCacheServiceImpl.CACHE_NAME,key = "#id")
    public void remove(Integer id){

        this.jpaBasDao.remove(this.jpaBasDao.get(TblStaffInfo.class,id));
    }
    //remove cache with key:#id where do update operation
    @CacheEvict(cacheNames = RedisCacheServiceImpl.CACHE_NAME,key = "#id")
    public void update(Integer id,String address){
        TblStaffInfo tsi = (TblStaffInfo)this.jpaBasDao.get(TblStaffInfo.class,id);
        tsi.setAddress(address);
        this.jpaBasDao.update(tsi);
    }

}
