package com.springLearn.springAnoEnableRelate.Import;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Import;

/*
*   将配置的CLass实例写入IOC容器
* */
@Import({Cat.class, Dog.class})
public class Test {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Test.class, args);
        Dog dog = context.getBean(Dog.class);
        Cat cat = context.getBean(Cat.class);
        System.out.println(dog.toString());
        System.out.println(cat.toString());
    }
}
