package com.Factory;

public class TestSimpleFactory {

    public static void main(String[] args) {
        FruitFactory appleFactory = new AppleFactory();
        Apple apple = (Apple) appleFactory.getFruit();
        apple.get();
    }
}
