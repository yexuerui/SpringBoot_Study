package com.AbstractFactory;

//抽象工厂角色，仅负责具体子类对象的实现接口
public interface FruitFactory {

    public Fruit getApple();

    public Fruit getBanana();
}
