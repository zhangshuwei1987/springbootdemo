package com.springLearn.springAnoEnableRelate.ImportBeanDefinitionRegistrar;

import com.springLearn.springAnoEnableRelate.Import.People;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.StandardAnnotationMetadata;

public class CustomImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {

    /*
    *  AnnotationMetadata:被注解类或方法的元信息
    *  BeanDefinitionRegistry：可以用来往spring容器中注入bean
    *
    * */

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {

        StandardAnnotationMetadata metadata = (StandardAnnotationMetadata) importingClassMetadata;

        //获取元类型或方法中所有注解的属性信息
        metadata.getAnnotationTypes().stream().forEach(entry ->{

        });
        //获取类中指定注解的参数值
        System.out.println(metadata.getAllAnnotationAttributes(EnableSomeThing.class.getName()));
        //注册Bean
        BeanDefinitionBuilder builder = BeanDefinitionBuilder.rootBeanDefinition(People.class);
        registry.registerBeanDefinition(People.class.getName(),builder.getBeanDefinition());

    }
}
