package com.AbstractFactory;

public class TestSimpleFactory {

    public static void main(String[] args) {
        FruitFactory appleFactory = new NorthFruitFactory();
        NorthApple apple = (NorthApple) appleFactory.getApple();
        apple.get();
    }
}
