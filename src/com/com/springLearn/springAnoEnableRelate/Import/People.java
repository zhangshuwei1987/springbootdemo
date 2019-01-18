package com.springLearn.springAnoEnableRelate.Import;

public class People {

    private String name = "people";



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "people-name="+name;
    }

}
