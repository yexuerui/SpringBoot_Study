package com.io.nio;


import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class TestNio {

    @Test
    public void nio1() throws IOException {
        long start = System.currentTimeMillis();
        FileInputStream fis = null;
        FileOutputStream fos = null;
        //①获取通道
        FileChannel inChannel = null;
        FileChannel outChannel = null;
        try {
            fis = new FileInputStream("src\\main\\resources\\aa2.png");
            fos = new FileOutputStream("src\\main\\resources\\112.png");
            inChannel = fis.getChannel();
            outChannel = fos.getChannel();
            //②分配指定大小的缓冲区
            ByteBuffer buf = ByteBuffer.allocate(1024);
            //③将通道中的数据存入缓冲区中
            while (inChannel.read(buf) != -1) {
                buf.flip(); //切换读取数据的模式
                //④将缓冲区中的数据写入通道中
                outChannel.write(buf);
                buf.clear(); //清空缓冲区
            }
        } finally {
            if (outChannel != null) {
                outChannel.close();
            }
            if (inChannel != null) {
                inChannel.close();
            }
            if (fos != null) {
                fos.close();
            }
            if (fis != null) {
                fis.close();
            }
        }
        long end = System.currentTimeMillis();
        System.out.println("耗费时间为：" + (end - start));
    }

}

