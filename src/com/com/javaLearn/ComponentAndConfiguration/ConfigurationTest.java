package com.javaLearn.ComponentAndConfiguration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/*
* @Configuration 申请为该注解的类将进行动态增强代理
* */
@Configuration
public class ConfigurationTest {

    @Bean
    public Driver driver(){
        Driver driver = new Driver();
        driver.setId(1);
        driver.setName("driver");
        driver.setCar(car());//这里的car()获取的实际上是从BeanFactory中获取的Bean-car实例
        return driver;
    }

    @Bean
    public Car car(){
        Car car = new Car();
        car.setId(1);
        car.setName("car");
        return car;
    }

}
