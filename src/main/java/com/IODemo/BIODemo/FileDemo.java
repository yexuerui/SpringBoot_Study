package com.IODemo.BIODemo;

import java.io.File;
import java.io.IOException;

public class FileDemo {
    public static void main(String[] args) throws IOException {
        //系统默认的分隔符字符
        System.out.println("系统默认的分隔符：" + File.separator);
        //系统相关的路径分隔符字符
        File file = new File("");
        System.out.println("获取工程的路径" + System.getProperty("user.dir"));
        System.out.println("绝对路径" + file.getAbsolutePath());
        System.out.println("绝对路径" + file.getCanonicalPath());
        System.out.println("传入的路径：" + file.getPath());
    }
}
