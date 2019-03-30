package com.SerializableDemo.unSerializable;

import java.io.*;

public class TestSerializable {
    public static void main(String[] args) throws IOException {
        Son son = new Son();
        //序列化
        //将对象读入ObjectOutputStream中，序列化
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(
                    new BufferedOutputStream(new FileOutputStream(new File("hello.txt"))));
//            ObjectOutputStream objectOutputStream = new ObjectOutputStream(
//                    new FileOutputStream(new File("hello.txt")));
            objectOutputStream.writeObject(son);
            //没有flush
            objectOutputStream.flush();  //没有flush掉数据
            //将对象读入ObjectInputStream中，反序列化
            ObjectInputStream objectInputStream = new ObjectInputStream(
                    new BufferedInputStream(new FileInputStream("hello.txt")));
//            ObjectInputStream objectInputStream = new ObjectInputStream(
//                    new FileInputStream("hello.txt"));
            Son readObject = (Son) objectInputStream.readObject();
            System.out.println("son：" + readObject);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
