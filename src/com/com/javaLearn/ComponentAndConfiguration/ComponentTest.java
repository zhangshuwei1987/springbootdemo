package com.javaLearn.ComponentAndConfiguration;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
/*
 * Component注解也会当做配置类，但是并不会为其生成CGLIB代理Class，所以在生成Driver对象时和生成Car对象时调用car()方法执行了两次new操作
 * */
@Component
public class ComponentTest {

    @Bean(name = "componentDriver")
    public Driver driver(){
        Driver driver = new Driver();
        driver.setId(1);
        driver.setName("driver");
        driver.setCar(car());//这里的car()则是普通的car方法的调用，不从BeanFactory中获取,若需引入Bean-car，则需使用注入方式@Autowired Car car
        return driver;
    }

    @Bean(name = "componentCar")
    public Car car(){
        Car car = new Car();
        car.setId(1);
        car.setName("car");
        return car;
    }

}
