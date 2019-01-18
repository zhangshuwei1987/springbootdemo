package com.framework.hibernate;

import org.springframework.context.annotation.Configuration;

@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@Configuration
public class SessionFactoryConfig {

    /*
     * 创建Hibernate-transaction-manager Bean
     * HibernateEntityManagerFactory hemf :引入hibernate-entity-manager 后自动申请的BEAN
     */
    /*@Bean
    public HibernateTransactionManager transactionManager(HibernateEntityManagerFactory hemf){
        return new HibernateTransactionManager(hemf.getSessionFactory());
    }*/

    //初始化全局的HIBERNATE-SESSIONFACTORY,spring Boot自动配置在存在该类型bean的情况下关闭其实体管理器
    /*
     *
     * HibernateEntityManagerFactory hemf :引入hibernate-entity-manager 后自动申请的BEAN
     */
    /*@Bean
    public SessionFactory sessionFactory(HibernateEntityManagerFactory hemf){
        return hemf.getSessionFactory();
    }*/

}
