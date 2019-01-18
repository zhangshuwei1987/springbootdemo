package com.javaLearn.Proxy;

public class TestDaoImpl implements TestDao{

    @Override
    public void save() {
        System.out.println("----已经保存数据!----");
    }
}
