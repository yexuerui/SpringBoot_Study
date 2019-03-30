package com.protoType;


import java.io.Serializable;
import java.util.List;

public class User implements Serializable {

    private String name;
    private List<String> sports;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getSports() {
        return sports;
    }

    public void setSports(List<String> sports) {
        this.sports = sports;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", sports=" + sports +
                '}';
    }
}
