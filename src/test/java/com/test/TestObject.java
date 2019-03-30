package com.test;

public class TestObject {

    public static int testFinally() {
        try {
            int i = 1 / 0;
            return 1;
        } catch (Exception e) {
            //可以看到，我们抛出的检查时异常，但是并未编译错误。
            System.out.println("我是打酱油的，我会不会被输出");
           throw new Exception("123");
        } finally {
            return 3;
        }
    }

    public static void main(String[] args) {
        System.out.println(TestObject.testFinally());
    }
}
