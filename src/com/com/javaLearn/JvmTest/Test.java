package com.javaLearn.JvmTest;

public class Test {

    public String getResult(TestBean tb){
        return tb.getCol3();
    }

    public static void main(String[] args) {
        TestBean tb = new TestBean("col1","col2");
        new Test().getResult(tb);
    }

}
