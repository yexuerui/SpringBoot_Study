package com.protoType;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class TestMyProtostuff {

    public static void main(String[] args) {
        Map<String, Object> map = new HashMap<>();
        map.put("key1", "小胖");
        map.put("key2", "小悠");
        map.put("key3", "小小");

        String str = "我爱中国";

        byte[] serializer = MyProtostuffUtils.serializer(str);
        System.out.println("序列化后的长度：" + serializer.length);
//        Map data = (HashMap) MyProtostuffUtils.deserialize(serializer, str.getClass());
        String data = MyProtostuffUtils.deserialize(serializer, str.getClass());
        System.out.println(data);
//        Iterator<Map.Entry<String, Object>> it = data.entrySet().iterator();
//        while (it.hasNext()) {
//            Map.Entry<String, Object> entry = it.next();
//            System.out.println(entry.getKey() + "---" + entry.getValue());
//        }

        //包装类型
//        MyProtostuffUtils.SerializeDeserializeWrapper wrapper = MyProtostuffUtils.SerializeDeserializeWrapper.builder(map);
//        System.out.println("wrapper对象的类型：" + wrapper.getClass().getName());
//        byte[] bytes = MyProtostuffUtils.serializer(wrapper);
//        System.out.println("序列化map集合的二进制长度是：" + bytes.length);
//        MyProtostuffUtils.SerializeDeserializeWrapper deserializer = MyProtostuffUtils.deserialize(bytes, wrapper.getClass());
//        System.out.println("Class对象：" + deserializer.getData().getClass());
//        Map data = (HashMap) deserializer.getData();
//        System.out.println("反序列化map对象是:" + data);
        //遍历map集合

    }

}
