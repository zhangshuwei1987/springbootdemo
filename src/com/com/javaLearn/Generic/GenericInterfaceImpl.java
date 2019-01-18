package com.javaLearn.Generic;

/*
* 泛型接口实现类
* 在实现类的继承接口定义类型（<String>）
* */
public class GenericInterfaceImpl<T> implements GenericInterface<String,Integer>{// 定义泛型接口的子类

    String var ;
    Integer yar;

    T impl;

    public GenericInterfaceImpl(String var,Integer yar,T impl){
        this.setVar(var);
        this.setYar(yar);
        this.setImpl(impl);
    }

    @Override
    public String getVar() {
        return var;
    }

    @Override
    public void setVar(String x) {
        var = x;
    }

    @Override
    public Integer getYar() {
        return yar;
    }

    @Override
    public void setYar(Integer yar) {
        this.yar = yar;
    }

    public T getImpl() {
        return impl;
    }

    public void setImpl(T impl) {
        this.impl = impl;
    }
}
//test
class GenericInterInterfaceTest{
    public static void main(String[] args) {
        GenericInterfaceImpl<Long> gci = new GenericInterfaceImpl<Long>("hello",123,321L);
        System.out.println("var="+gci.getVar()) ;
        System.out.println("yar="+gci.getYar()) ;
        System.out.println("impl="+gci.getImpl()) ;
    }
};
