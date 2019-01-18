package com.javaLearn.Proxy;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Cglib子类代理工厂
 * 对UserDao在内存中动态构建一个子类对象
 */
public class CglibProxyFactory implements MethodInterceptor{
    //维护目标对象
    private Object target;

    public CglibProxyFactory(Object target) {
        this.target = target;
    }

    //给目标对象创建一个代理对象
    public Object getProxyInstance(){
        //1.增强器
        Enhancer en = new Enhancer();
        //2.设置父类
        en.setSuperclass(target.getClass());
        //3.设置回调函数
        en.setCallback(this);
        //4.创建子类(代理对象)
        return en.create();

    }
    //定义拦截器拦截法Bean中方的执行并添加自定义逻辑
    //proxyObj:代理对象
    /*
    * 定义拦截器拦截法Bean中方的执行并添加自定义逻辑
    * proxyObj:代理对象
    * method：目标方法
    * args：方法参数
    * methodProxy:代理方法
    * */
    @Override
    public Object intercept(Object proxyObj, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        System.out.println("开始事务...");

        //执行目标对象的方法
        Object returnValue = method.invoke(target, args);

        System.out.println("提交事务...");

        return returnValue;
    }
}

class CglibProxyFactoryTest{
    public static void main(String[] args) {
        TestDaoImpl target = new TestDaoImpl();
        System.out.println(target.getClass());
        TestDaoImpl proxy = (TestDaoImpl) new CglibProxyFactory(target).getProxyInstance();
        System.out.println(proxy.getClass());
        proxy.save();
    }
}
