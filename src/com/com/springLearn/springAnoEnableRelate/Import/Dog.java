package com.springLearn.springAnoEnableRelate.Import;

public class Dog {
    private String name = "dog";



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "dog-name="+name;
    }
}
