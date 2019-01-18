package com.springLearn.profileAnnotation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/*
* @Profile注解提供根据不同的profile配置加载不同Bean的功能
*
*/
@Configuration
public class Test {

    @Bean
    @Profile("dev")
    public String devTest() {
        return "dev";
    }

    @Bean
    @Profile("prod")
    public String prodTest() {
        return "prod";
    }


}
