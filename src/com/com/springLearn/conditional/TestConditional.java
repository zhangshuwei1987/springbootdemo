package com.springLearn.conditional;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestConditional {

    /*
    * @Conditional 自动实例化所配置的class，并执行matchs方法返回布尔型判断是否加载当前@Conditional所在的bean
    * */
    @Bean
    @Conditional(TestTypeCondition.class)
    public String conditionTest(){
        return new String("conditionTest");
    }

    @Bean
    @ConditionAnnotation("annoConditionTest")
    public String annoConditionTest(){
        return new String("annoConditionTest");
    }
}
