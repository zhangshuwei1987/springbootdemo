package com.framework.transaction;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class ZswTraConfig {

    /*
        查看当前默认使用的事务管理类类型。（JpaTransactionManager）
        Jpa事务管理器可以管理JDBC事务，故当JPA与JDBC在事务中混用时使用JPA事务管理器即可。
     */
    @Bean
    public Object testBean(PlatformTransactionManager platformTransactionManager){
            System.out.println(">>>>>>>>>>" + platformTransactionManager.getClass().getName());
            return new Object();
    }
}
