package com.javaLearn.Generic;

public class GenericMethod {

    //静态函数
    public static  <T> void StaticMethod(T a){
        System.out.println("harvic StaticMethod: "+a.toString());
    }
    //普通函数
    public  <T> void OtherMethod(T a){
        System.out.println("harvic OtherMethod: "+a.toString());
    }

    //存在返回值的函数
    //Class<T> clazz:泛型类对象
    public static <T> T createInstance(Class<T> clazz) throws IllegalAccessException, InstantiationException {
        return clazz.newInstance();
    }


}

class GenericMethodTest{


    public static void main(String[] args) throws InstantiationException, IllegalAccessException {

        GenericClass<String,Integer,Long> gc = new GenericClass<String,Integer,Long>();

        //静态方法
        GenericMethod.StaticMethod("adfdsa");//使用方法一
        GenericMethod.<String>StaticMethod("adfdsa");//使用方法二
        GenericMethod.createInstance(gc.getClass());

        //常规方法
        GenericMethod staticFans = new GenericMethod();
        staticFans.OtherMethod(new Integer(123));//使用方法一
        staticFans.<Integer>OtherMethod(new Integer(123));//使用方法二
    }

}
