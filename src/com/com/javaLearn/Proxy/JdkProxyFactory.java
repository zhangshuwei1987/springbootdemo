package com.javaLearn.Proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 创建动态代理对象
 * 动态代理不需要实现接口,但是需要指定接口类型
 */
public class JdkProxyFactory{

    //维护一个目标对象
    private Object target;
    public JdkProxyFactory(Object target){
        this.target=target;
    }

    public Object getProxyInstance(){
        return Proxy.newProxyInstance(
                target.getClass().getClassLoader(),//获得类加载器
                target.getClass().getInterfaces(),//获取class对象的接口
                new InvocationHandler() {
                    /*
                    * 类似CGLIB的intercept，添加对target对象方法的拦截
                    * */
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        System.out.println("开始事务2");
                        //运用反射执行目标对象方法
                        Object returnValue = method.invoke(target, args);
                        System.out.println("提交事务2");
                        return returnValue;
                    }
                }
        );
    }

}

class proxyFactoryTest{
    public static void main(String[]args){
        //    我们要代理的真实对象
        TestDao target = new TestDaoImpl();
        System.out.println(target.getClass());

        TestDao proxy = (TestDao) new JdkProxyFactory(target).getProxyInstance();
        System.out.println(proxy.getClass());
        proxy.save();
    }
}
