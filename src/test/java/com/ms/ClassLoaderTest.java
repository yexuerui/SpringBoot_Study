package com.ms;

import java.io.IOException;
import java.io.InputStream;

//测试类加载器
public class ClassLoaderTest {
    public static void main(String[] args) {

        //定义类加载器（局部内部类）
        ClassLoader myLoader = new ClassLoader() {
            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException {
                //获取文件名.class格式的
                try {
                    String fileName = name.substring(name.lastIndexOf(".") + 1) + ".class";
                    InputStream is = getClass().getResourceAsStream(fileName);
                    if (is == null) {
                        return super.loadClass(name);
                    }
                    byte[] bytes = new byte[is.available()];
                    is.read(bytes);
                    return defineClass(name, bytes, 0, bytes.length);
                } catch (IOException e) {
                    throw new ClassNotFoundException("没有找到对应的class类！");
                }
            }
        };
        try {
            //同一个Class文件，被不同的类加载器所加载
            Object obj = myLoader.loadClass("com.ms.ClassLoaderTest").newInstance();
            System.out.println(obj.getClass());
            //那么这两个类必定不相等
            System.out.println(obj instanceof com.ms.ClassLoaderTest);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }
}