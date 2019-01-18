package com.springLearn.springAnoEnableRelate.ImportSelector;


import com.springLearn.springAnoEnableRelate.Import.Cat;
import com.springLearn.springAnoEnableRelate.Import.Dog;
import com.springLearn.springAnoEnableRelate.Import.People;
import com.springLearn.springAnoEnableRelate.Import.Robbet;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

@EnableLog
public class MainTest {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(MainTest.class,args);
        System.out.println(context.getBean(People.class));
        System.out.println(context.getBean(Robbet.class));
        System.out.println(context.getBean(Dog.class));
        System.out.println(context.getBean(Cat.class));

    }

}
