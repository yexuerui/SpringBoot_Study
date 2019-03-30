package com.protoType;

import io.protostuff.LinkedBuffer;
import io.protostuff.ProtostuffIOUtil;
import io.protostuff.Schema;
import io.protostuff.runtime.RuntimeSchema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/***
 * CACHE_SCHEMA  缓冲区。K-V 分别是class（类结构信息）对象和Schema（模板）对象
 *
 */
public class MyProtostuffUtils {
    private static Logger logger = LoggerFactory.getLogger(MyProtostuffUtils.class);
    //将数据封装
    private static final Set<Class<?>> WRAPPER_SET = new HashSet<>();

    //包装类的Class对象
    private static final Class<SerializeDeserializeWrapper> WRAPPER_CLASS = SerializeDeserializeWrapper.class;

    //包装类的Schema对象
    private static final Schema<SerializeDeserializeWrapper> WRAPPER_SCHEMA = RuntimeSchema.createFrom(WRAPPER_CLASS);

    //安全缓存区，class对象和Schema对象
    private static final Map<Class<?>, Schema<?>> CACHE_SCHEMA = new ConcurrentHashMap<>();

    static {
        WRAPPER_SET.add(List.class);
        WRAPPER_SET.add(ArrayList.class);
        WRAPPER_SET.add(CopyOnWriteArrayList.class);
        WRAPPER_SET.add(LinkedList.class);
        WRAPPER_SET.add(Stack.class);
        WRAPPER_SET.add(Vector.class);
        WRAPPER_SET.add(Map.class);
        WRAPPER_SET.add(HashMap.class);
        WRAPPER_SET.add(TreeMap.class);
        WRAPPER_SET.add(LinkedHashMap.class);
        WRAPPER_SET.add(Hashtable.class);
        WRAPPER_SET.add(SortedMap.class);
//        WRAPPER_SET.add(Object.class);
    }

    //注册需要使用包装类进行序列化的Class对象
    public static void registerWrapperClass(Class clazz) {
        WRAPPER_SET.add(clazz);
    }

    //获取序列化对象类型的schema
    private static <T> Schema<T> getSchema(Class<T> clazz) {
        Schema<T> schema = (Schema<T>) CACHE_SCHEMA.get(clazz);
        if (schema == null) {
            schema = RuntimeSchema.createFrom(clazz);
            CACHE_SCHEMA.put(clazz, schema);
        }
        return schema;
    }

    //序列化对象
    public static <T> byte[] serializer(T obj) {
        //获取序列化对象
        Class<T> clazz = (Class<T>) obj.getClass();
        //设置缓数组缓冲区
        LinkedBuffer buffer = LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE);
        byte[] bytes = null;
        try {
            Object serializerObj = obj;       //获取序列化对象
            Schema schema = WRAPPER_SCHEMA;   //获取Schema对象
            //包装class对象
            if (WRAPPER_SET.contains(clazz)) {
                //外部类是否可以使用静态内部类的成员？【外部类使用内部类的成员，需要新建内部类实例。】
                serializerObj = SerializeDeserializeWrapper.builder(obj);//将class对象进行包装
            } else {
                //将class对象和schema对象保存到hashMap中
                schema = getSchema(clazz);  //获取Schema对象
            }
            //将对象转换为字节流
            bytes = ProtostuffIOUtil.toByteArray(serializerObj, schema, buffer);
        } catch (Exception e) {
            logger.info("序列化{}失败", obj, e);
            throw new IllegalStateException(e.getMessage());
        } finally {
            //回收buffer
            buffer.clear();
        }
        return bytes;
    }


    public static <T> T deserialize(byte[] data, Class<T> clazz) {
        try {
            //判断是否是不可序列化对象，若是不能序列化对象，将对象进行包装
            if (WRAPPER_SET.contains(clazz)) {
                //SerializeDeserializeWrapper<T> wrapper = SerializeDeserializeWrapper.builder(clazz.newInstance());
                SerializeDeserializeWrapper<T> wrapper = new SerializeDeserializeWrapper<>();
                ProtostuffIOUtil.mergeFrom(data, wrapper, WRAPPER_SCHEMA);
                return wrapper.getData();
            } else {
                T message = clazz.newInstance();
                Schema<T> schema = getSchema(clazz);
                ProtostuffIOUtil.mergeFrom(data, message, schema);
                return message;
            }
        } catch (Exception e) {
            logger.error("反序列化对象异常 [" + clazz.getName() + "]", e);
            throw new IllegalStateException(e.getMessage(), e);
        }
    }



    //静态内部类
    public static class SerializeDeserializeWrapper<T> {
        //泛型的使用
        private T data;

        //建造者模式(返回实体类型)
        public static <T> SerializeDeserializeWrapper<T> builder(T data) {
            SerializeDeserializeWrapper<T> wrapper = new SerializeDeserializeWrapper<T>();
            wrapper.setData(data);
            return wrapper;
        }

        public void setData(T data) {
            this.data = data;
        }

        public T getData() {
            return data;
        }
    }
}