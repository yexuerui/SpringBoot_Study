package com.IODemo.BIODemo;

import java.io.*;
import com.JsonSerializer.User;
public class ObjectOut {
    public static void main(String[] args) {
        //将文件读入到内存中
        try {
            InputStream inputStream = new FileInputStream("user.txt");
            BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
            ObjectInputStream objectInputStream = new ObjectInputStream(bufferedInputStream);
            User readObject = (User)objectInputStream.readObject();
            System.out.println(readObject);
            System.out.println(User.STATE_STR);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
