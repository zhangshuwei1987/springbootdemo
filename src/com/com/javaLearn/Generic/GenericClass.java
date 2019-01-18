package com.javaLearn.Generic;
/*
* 多泛型类,泛型名可自定
* */
public class GenericClass<A,B,C> {

    private A x;

    private B y;

    private C z;

    public A getX() {
        return x;
    }

    public void setX(A x) {
        this.x = x;
    }

    public B getY() {
        return y;
    }

    public void setY(B y) {
        this.y = y;
    }

    public C getZ() {
        return z;
    }

    public void setZ(C z) {
        this.z = z;
    }
}
class GenericTest {

    public static void main(String[] args) {

        GenericClass<String,Integer,Long> gc = new GenericClass<String,Integer,Long>();
        gc.setX("123");
        gc.setY(123);
        gc.setZ(123L);
        System.out.println("x坐标===="+gc.getX());
    }
}
