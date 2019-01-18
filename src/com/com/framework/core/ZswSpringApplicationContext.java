package com.framework.core;

import com.framework.core.context.ApplicationContextProvider;
import com.framework.core.util.ProxyBeanAopTargetUtil;
import org.springframework.context.ApplicationContext;

public class ZswSpringApplicationContext {


    public static ApplicationContext getSpringContext(){
        return ApplicationContextProvider.getApplicationContext();
    };

    /**
     * 通过name获取 Bean(存在代理的话则).
     * @param name
     * @return
     */
    public static Object getBean(String name){
        return ApplicationContextProvider.getBean(name);
    }

    /**
     * 通过class获取Bean.
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T getBean(Class<T> clazz){
        return ApplicationContextProvider.getBean(clazz);
    }

    //获取代理Bean（如果存在代理的话由CGLIB或JDK代理）的target对象
    public static <T> T getSourceBeanFromSpringContext(Class<T> beanClass){
        return (T) ProxyBeanAopTargetUtil.getTarget(ApplicationContextProvider.getBean(beanClass));
    }

    //获取代理Bean（如果存在代理的话由CGLIB或JDK代理）的target对象
    public static <T> T getSourceBeanFromSpringContextByBeanName(String beanName){
        return (T) ProxyBeanAopTargetUtil.getTarget(ApplicationContextProvider.getBean(beanName));
    }

}
