package com.simpleFactory;
//具体产品对象
public class Banana implements Fruit{
    @Override
    public void get() {
        System.out.println("采集香蕉");
    }
}
