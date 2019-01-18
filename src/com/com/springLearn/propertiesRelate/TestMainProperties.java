package com.springLearn.propertiesRelate;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/*
* 从主配置文件中加载属性
* */
/*
* @ConfigurationProperties使用的前提必须加入@EnableConfigurationProperties
* 使用Relaxed binding
* 使用ConfigurationPropertiesBindingPostProcessor类绑定@PropertySources到带注释的beanConfigurationProperties.
* 如果要绑定和验证某些外部属性(例如，来自.properties文件)，则将其添加到@Configuration类中的类定义或@Bean方法中。
*
* */
@Configuration
@ConfigurationProperties(prefix = "test") //读applicaiton或相应Profile中的对应前缀的属性值
public class TestMainProperties {

    private String uploadPath;

    public String getUploadPath() {
        return uploadPath;
    }

    public void setUploadPath(String uploadPath) {
        this.uploadPath = uploadPath;
    }
}
