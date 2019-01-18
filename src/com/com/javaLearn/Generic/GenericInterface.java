package com.javaLearn.Generic;

/*
* 泛型接口
* */
public interface GenericInterface<X,Y> {// 在接口上定义泛型

    public X getVar() ; // 定义抽象方法，抽象方法的返回值就是泛型类型
    public void setVar(X x);

    public Y getYar();

    public void setYar(Y y);

}
