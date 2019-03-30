package com.protoType;

import java.util.ArrayList;
import java.util.List;

public class TestClone {

    //测试深浅克隆
    public static void main(String[] args) {
        //创建用户对象
        User user = new User();
        user.setName("黎明");
        List sports = new ArrayList<>();
        sports.add("足球");
        sports.add("游泳");
        user.setSports(sports);
        //实现深度克隆

        String str = "我爱中国";
        String s = ProtostuffUtil.clone(str);
        System.out.println("序列化后的对象：" + s);


        List list = ProtostuffUtil.clone(sports);
        for (Object obj : list) {
            System.out.println("obj对象:" + obj);
        }


//        User clone = ProtostuffUtil.clone(user);


//        System.out.println("对象地址是否相等：" + (clone == user));
//        System.out.println("对象class是否相等：" + (clone.getClass() == user.getClass()));
//        System.out.println("clone对象："+clone.toString());
//        //修改clone对象，观看user对象是否改变
//        clone.getSports().set(0,"跑步");
//        System.out.println("修改后的clone对象："+clone.toString());
//        System.out.println("user对象是否发生改变"+user);
    }
}
