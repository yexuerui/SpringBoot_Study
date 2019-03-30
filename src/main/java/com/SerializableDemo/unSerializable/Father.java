package com.SerializableDemo.unSerializable;

import java.io.Serializable;

public class Father implements Serializable {

    private static final long serialVersionUID = 6132242367504858213L;
    private Integer fatherage;
    private String str;

    public Father() {
        fatherage=50;
        str="I am father";
    }

    public Integer getFatherage() {
        return fatherage;
    }

    public void setFatherage(Integer fatherage) {
        this.fatherage = fatherage;
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    @Override
    public String toString() {
        return "Father{" +
                "fatherage=" + fatherage +
                ", str='" + str + '\'' +
                '}';
    }
}
