package com.springLearn.springAnoEnableRelate.ImportBeanDefinitionRegistrar;

import com.springLearn.springAnoEnableRelate.Import.People;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

@EnableSomeThing("testStr") //开启某个功能
public class MainTest {

    public static void main(String[] args) {
       ConfigurableApplicationContext context = SpringApplication.run(MainTest.class,args);
        System.out.println(context.getBean(People.class));
    }

}
