package com.zhangsw.springbootdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
/*
 * 启用对ConfigurationProperties注释bean的支持。
 * ConfigurationProperties bean可以标准方式注册(例如使用@Bean方法)，或者为了方便，可以在将这个注释所在的CLASS或者MEHTOD直接指定为Bean。
*/
@EnableConfigurationProperties
@ComponentScan(basePackages = {"com.framework", "com.zhangsw", "com.springLearn"})
//使用hibernate需指定entity包路径
@EntityScan(basePackages = {"com.framework.entity", "com.zhangsw.springbootdemo.entity"})
@EnableJpaRepositories(basePackages = {"com.zhangsw.springbootdemo.dao","com.framework.example.dao"})//开启jpa-repositories并扫描指定的包
@EnableTransactionManagement//开启事务支持，开启之后只需要SERVICE层添加@Transactional即可加入事务管理
@EnableCaching //开启spring-cache支持
@ServletComponentScan
//开启SERVLET组件扫描。配置druid必须加的注解，如果不加，访问页面打不开，filter和servlet、listener之类的需要单独进行注册才能使用，spring boot里面提供了该注解起到注册作用
public class SpringbootdemoApplication extends SpringBootServletInitializer {

    private static ApplicationContext applicationContext;

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        // 注意这里要指向原先用main方法执行的Application启动类
        return builder.sources(SpringbootdemoApplication.class);
    }


    public static void main(String[] args) {

        applicationContext = SpringApplication.run(SpringbootdemoApplication.class, args);
    }


}
