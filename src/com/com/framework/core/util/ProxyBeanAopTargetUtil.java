package com.framework.core.util;

import org.springframework.aop.framework.AdvisedSupport;
import org.springframework.aop.framework.AopProxy;
import org.springframework.aop.support.AopUtils;

import java.lang.reflect.Field;

public class ProxyBeanAopTargetUtil {

    public static Object getTarget(Object proxy) {

        if (AopUtils.isJdkDynamicProxy(proxy)) {
            return getJdkDynamicProxyTargetObject(proxy);
        } else if(AopUtils.isCglibProxy(proxy)){ // cglib
            return getCglibProxyTargetObject(proxy);
        }else{
            return proxy;// 不是代理对象
        }
    }

    private static Object getCglibProxyTargetObject(Object proxy) {
        try{
            Field h = proxy.getClass().getDeclaredField("CGLIB$CALLBACK_0");
            h.setAccessible(true);
            Object dynamicAdvisedInterceptor = h.get(proxy);
            Field advised = dynamicAdvisedInterceptor.getClass().getDeclaredField("advised");
            advised.setAccessible(true);
            Object target = ((AdvisedSupport) advised.get(dynamicAdvisedInterceptor)).getTargetSource().getTarget();
            return target;
        } catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }


    private static Object getJdkDynamicProxyTargetObject(Object proxy) {
        try{
            Field h = proxy.getClass().getSuperclass().getDeclaredField("h");
            h.setAccessible(true);
            AopProxy aopProxy = (AopProxy) h.get(proxy);
            Field advised = aopProxy.getClass().getDeclaredField("advised");
            advised.setAccessible(true);
            Object target = ((AdvisedSupport) advised.get(aopProxy)).getTargetSource().getTarget();
            return target;
        } catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }


}
