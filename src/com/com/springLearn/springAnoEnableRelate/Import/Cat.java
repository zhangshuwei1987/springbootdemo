package com.springLearn.springAnoEnableRelate.Import;

public class Cat {

    private String name = "cat";



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "cat-name="+name;
    }

}
