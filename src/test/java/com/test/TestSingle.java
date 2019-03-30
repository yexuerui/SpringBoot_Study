package com.test;
//单例测试
public class TestSingle {
    //私有得到构造方法(懒加载的同时，保证了线程安全)
    private  TestSingle(){
    }
    //使用静态内部类实现构造函数
    static class SingleHolder{
        //对象的实例化是在内部类加载的时候构建的，因此改版是线程安全的
        //调用只有在方法时，该类才会被加载，起到了懒加载的作用
        private static  final TestSingle testSingle=new TestSingle();
    }
    public static TestSingle getSingleInstance(){
        return SingleHolder.testSingle;
    }
}
