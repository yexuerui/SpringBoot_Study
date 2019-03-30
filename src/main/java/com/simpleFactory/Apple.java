package com.simpleFactory;

//具体产品对象
public class Apple implements Fruit{
    @Override
    public void get() {
        System.out.println("采集苹果");
    }
}
