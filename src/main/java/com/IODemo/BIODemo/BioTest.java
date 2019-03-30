package com.IODemo.BIODemo;


import com.JsonSerializer.User;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.time.LocalDate;

public class BioTest {

    public static void main(String[] args) throws IOException {

        User user = new User();
        user.setDate(LocalDate.now());
        user.setName("tom");
        System.out.println(User.STATE_STR);
        //对象持久化到文本
        FileOutputStream fileOutputStream = null;
        BufferedOutputStream bufferedOutputStream = null;
        ObjectOutputStream objectOutputStream = null;

        try {
            fileOutputStream = new FileOutputStream("user.txt");
            bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
            //使用ObjectOutputStream包装
            objectOutputStream = new ObjectOutputStream(bufferedOutputStream);
            objectOutputStream.writeObject(user);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            objectOutputStream.close();
        }

    }
}
