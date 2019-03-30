package com.SerializableDemo.unSerializable;

public class Son extends Father {

    private static final long serialVersionUID = -7351906785492146228L;

    private int age;

    private transient String unserialize;

    public Son() {
        age = 10;
        unserialize = "hi";
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getUnserialize() {
        return unserialize;
    }

    public void setUnserialize(String unserialize) {
        this.unserialize = unserialize;
    }

    @Override
    public String toString() {
        return "Son{" +
                "age=" + age +
                ", unserialize='" + unserialize + '\'' +
                '}';
    }
}
