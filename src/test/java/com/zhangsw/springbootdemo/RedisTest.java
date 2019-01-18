package com.zhangsw.springbootdemo;

import com.framework.dao.RedisTemplateDaoImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTest {

    @Autowired
    private RedisTemplateDaoImpl redisTemplateDao;
    @Test
    public void test(){
        redisTemplateDao.save("fuck1234","1231313");
    }
    @Test
    public void get(){
        redisTemplateDao.get("fuck123");
    }
}
