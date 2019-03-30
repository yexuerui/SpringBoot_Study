package com.simpleFactory;

//静态工厂
public class FruitFactory {

    //静态工厂方法，获取各个对象实例
    public static Fruit getFruit(String type) throws IllegalAccessException, InstantiationException {
        if (type.equalsIgnoreCase("apple")) {
            //通过反射创建对象
            return Apple.class.newInstance();
        } else if (type.equalsIgnoreCase("banana")) {
            return Banana.class.newInstance();
        } else {
            throw new RuntimeException("type is illegal");
        }
    }

    //静态方法获取对象实例
    public static Fruit getFruitByClassName(String className) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Class<?> name = Class.forName(className);
        return (Fruit) name.newInstance();
    }

}
