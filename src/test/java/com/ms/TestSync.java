package com.ms;

public class TestSync {

    public  synchronized void method1(){
        System.out.println("打印1");
        method2();
    }

    private synchronized void method2() {
        System.out.println("打印2");
    }

    public static void main(String[] args) {
        new TestSync().method1();
    }
}
