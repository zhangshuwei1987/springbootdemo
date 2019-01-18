package com.springLearn.springAnoEnableRelate.Import;

public class Robbet {

    private String name = "Robbet";



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Robbet-name="+name;
    }

}
