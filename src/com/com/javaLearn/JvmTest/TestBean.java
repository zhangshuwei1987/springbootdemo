package com.javaLearn.JvmTest;

public class TestBean {

    private String col1;

    private String col2;

    private String col3;

    public TestBean(String col1,String col2){
        this.col1 = col1;
        this.col2 = col2;
        this.col3 = col1+","+col2;
    }

    public String getCol1() {
        return col1;
    }

    public void setCol1(String col1) {
        this.col1 = col1;
    }

    public String getCol2() {
        return col2;
    }

    public void setCol2(String col2) {
        this.col2 = col2;
    }

    public String getCol3() {
        return col3;
    }

    public void setCol3(String col3) {
        this.col3 = col3;
    }
}
