package com.simpleFactory;

public class TestSimpleFactory {

    public static void main(String[] args) {
        try {
            Apple apple = (Apple) FruitFactory.getFruit("apple");
            Banana banana = (Banana) FruitFactory.getFruit("banana");
            apple.get();
            banana.get();
            //方法二获取实例对象
            Apple apple1 = (Apple) FruitFactory.getFruitByClassName("com.simpleFactory.Apple");
            apple1.get();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
