package com.zhangsw.springbootdemo;

import com.framework.service.TblAttachmentServiceImpl;
import com.javaLearn.ComponentAndConfiguration.Car;
import com.javaLearn.ComponentAndConfiguration.ConfigurationTest;
import com.javaLearn.ComponentAndConfiguration.Driver;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ComponentAndConfigurationTest {

    @Autowired
    private Car car;

    @Autowired
    private Driver driver;

    @Resource
    private Driver componentDriver;

    @Resource
    private Car componentCar;

    @Autowired
    TblAttachmentServiceImpl attachmentService;

    @Autowired
    ConfigurationTest configurationTest;

    @Test
    public void testConfiguration(){
        boolean result = driver.getCar() == car;
        System.out.println(result ? "同一个car" : "不同的car");
    }

    @Test
    public void testComponent(){
        boolean result = componentDriver.getCar() == componentCar;
        System.out.println(result ? "同一个car" : "不同的car");
    }

}
