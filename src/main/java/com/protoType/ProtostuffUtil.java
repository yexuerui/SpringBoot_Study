package com.protoType;

import io.protostuff.LinkedBuffer;
import io.protostuff.ProtostuffIOUtil;
import io.protostuff.Schema;
import io.protostuff.runtime.RuntimeSchema;

//工厂方法，实现序列化接口DeepClone方法。

public class ProtostuffUtil {

    //序列化
    public static <T> byte[] serializer(T obj) {
        Class<T> clazz = (Class<T>) obj.getClass();
        LinkedBuffer buffer = LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE);

        try {
            Schema<T> schema = RuntimeSchema.getSchema(clazz);
            byte[] bytes = ProtostuffIOUtil.toByteArray(obj, schema, buffer);
            return bytes;
        } catch (Exception e) {
            throw new RuntimeException("序列化失败...");
        } finally {
            buffer.clear();
        }
    }

    //反序列化
    public static <T> T deserializer(byte[] data, Class<T> clazz) {
        if (data == null || data.length == 0) {
            throw new RuntimeException("反序列化失败，byte[]不能为空");
        }
        T obj = null;
        try {
            obj = clazz.newInstance();
            Schema<T> schema = RuntimeSchema.getSchema(clazz);
            ProtostuffIOUtil.mergeFrom(data, obj, schema);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return obj;
    }

    public static <T> T clone(T obj) {
        long startTime = System.currentTimeMillis();
        T deserializer = (T) deserializer(serializer(obj), obj.getClass());
        long endTime = System.currentTimeMillis();
        System.out.println(endTime - startTime);
        return deserializer;
    }
    //https://bugyun.iteye.com/blog/2325698
}
