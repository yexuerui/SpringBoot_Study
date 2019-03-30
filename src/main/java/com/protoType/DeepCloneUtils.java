package com.protoType;

import java.io.*;
public class DeepCloneUtils implements Serializable {

    public static <T> T clone(T sourceObj) {

        long startTime = System.currentTimeMillis();
        ByteArrayOutputStream byteArrayOutputStream = null;
        ObjectOutputStream objectOutputStream = null;
        ByteArrayInputStream byteArrayInputStream = null;
        ObjectInputStream objectInputStream = null;
        Object target = null;

        try {
            //序列化操作(将对象转成流，out出去)
            byteArrayOutputStream = new ByteArrayOutputStream();
            objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(sourceObj);
            //反序列化操作(流转成对象，in进来)
            byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
            objectInputStream = new ObjectInputStream(byteArrayInputStream);
            target = objectInputStream.readObject();
            long endTime = System.currentTimeMillis();
            System.out.println("执行时间："+(endTime-startTime));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                //数组关闭
                byteArrayInputStream = null;
                byteArrayOutputStream = null;
                if (objectOutputStream != null) {
                    objectOutputStream.close();
                }
                if (objectInputStream != null) {
                    objectInputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return (T) target;
    }

}
