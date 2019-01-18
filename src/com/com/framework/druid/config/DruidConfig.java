package com.framework.druid.config;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.druid.pool.DruidDataSource;

@Configuration  //标识该类被纳入spring容器中实例化并管理

public class DruidConfig {

	@Bean
	@ConfigurationProperties(prefix="spring.datasource") //加载时读取指定的配置信息,前缀为spring.datasource
	public DataSource druidDataSource() {
		DruidDataSource druidDataSource = new DruidDataSource();  
        return druidDataSource;  
	}

}
