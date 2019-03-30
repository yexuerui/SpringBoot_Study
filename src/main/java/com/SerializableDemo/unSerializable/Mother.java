package com.SerializableDemo.unSerializable;

public class Mother {
    private Integer matherage;

    private String str;

    public Mother() {
        matherage = 30;
        str = "I am mother";
    }

    public Integer getMatherage() {
        return matherage;
    }

    public void setMatherage(Integer matherage) {
        this.matherage = matherage;
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }
}
