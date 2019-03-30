package com.reflexDemo;

import com.reflexDemo.Entity.Box;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ReflexTest {

    //反射之知多少
    public static void main(String[] args) {

//        TypeReference<String> a = new TypeReference<String>() {
//        };

        Box<String> box = new Box<String>() {
        };
//        Box box = new Box(){};
        Map<String, String> map = new HashMap<>();
        map.put("hello", "world");
        map.put("你好", "世界");
        ArrayList<Integer> list = new ArrayList<>();

        //泛型通配符，上限有界通配符
        //
        Type aType = map.getClass().getGenericSuperclass();//获取泛型类型
        //Class是Type的接口的子接口
        //Type接口不是泛型接口
//        Type aType = aClass.getGenericSuperclass();  //CLass对象进行了泛型擦除
        //子类——>父类不需要强转，但是父类——>子类需要强转
        //[pə'ræmɪtəraɪzd] 参数化的

        System.out.println(aType instanceof ParameterizedType);
        if (aType instanceof ParameterizedType) {
            //取第一个参数类型
            Type actualTypeArguments = ((ParameterizedType) aType).getActualTypeArguments()[0];
            System.out.println(actualTypeArguments.getTypeName());

        }
    }
}
