package com.generic;

import java.io.Serializable;
import java.util.ArrayList;

public class TestGenerics {
    public class Box<T> {

        private T t;
//
//        public Box(T t) {
//            this.t = t;
//        }

        public T getT() {
            return t;
        }

        public void setT(T t) {
            this.t = t;
        }

        public void add(T t) {
            System.out.println("box输入的值：" + t);
        }

        //实例内部类中不应该含有静态方法或者变量
        public <T> T pick(T a1, T a2) {
            return a2;
        }
    }

    public static void main(String[] args) {
        TestGenerics generics = new TestGenerics();
        Box<Number> box = generics.new Box<>();
        //推断返回的类型是Serializable类型
        Serializable d = box.pick("d", new ArrayList<String>());
        System.out.println(d.getClass().getName());
    }
}
