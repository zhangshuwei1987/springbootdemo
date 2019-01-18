package com.springLearn.springAnoEnableRelate.ImportBeanDefinitionRegistrar;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(CustomImportBeanDefinitionRegistrar.class)
public @interface EnableSomeThing {

    String value() default "";

}
