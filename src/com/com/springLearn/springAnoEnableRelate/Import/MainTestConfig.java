package com.springLearn.springAnoEnableRelate.Import;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Import;

/*
* 导入类，其中存在@Bean-method，也可将@Bean-method-result写入IOC容器
* */
@Import(ImportClassConfig.class)
public class MainTestConfig {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(MainTestConfig.class,args);
        Dog dog = context.getBean(Dog.class);
        Cat cat = context.getBean(Cat.class);
        System.out.println(dog.toString());
        System.out.println(cat.toString());
    }

}
