package com.proxy;


import sun.misc.ProxyGenerator;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Proxy;

//测试代理模式
public class TestProxy {

    public static void main(String[] args) {
        //目标类代码
        StudentDao studentDao = new StudentDaoImpl();
        //我们首先是需要准备一个代理类
        MyInvocationHandler handler = new MyInvocationHandler(studentDao);
        //第一个参数是：指定代理类的类加载器（一般都是双亲委托模式）
        //第二个参数是代理类需要实现的接口（我们传入的是目标类实现的接口，于是代理类和实现类拥有相同的接口）
        //第三个参数是invocation handler，用来处理方法的调用这里传入自己实现的handler
        Class<?>[] interfaces = studentDao.getClass().getInterfaces();
        //打印接口
        for (Class inter : interfaces) {
            System.out.println("第二个参数：" + inter);
        }
        //其实他是一个对象，代理对象就是目标对象上加了解耦的逻辑。
        StudentDao stuProxy = (StudentDao) Proxy.newProxyInstance(studentDao.getClass().getClassLoader(),
                studentDao.getClass().getInterfaces(), handler);
        //需要注意的是：并没有像静态代理那样去自己定义一个代理类，并实例化代理对象。
        //实际上，动态代理对象是在内存中的，是我们JDK根据自己传入的参数生成好的。
        stuProxy.login("tom", "123");
        //生成代理对象的Class对象
        String path = "D://11/$Proxy0.class";
        byte[] classFile = ProxyGenerator.generateProxyClass("$Proxy0", StudentDaoImpl.class.getInterfaces());
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(path);
            out.write(classFile);
            out.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}

