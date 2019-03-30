package com.zhuangshi;

public class TestDecorator {

    public static void main(String[] args) {
        User user=new VipDecorator(new User());
        System.out.println(user.getDes());
        System.out.println(((VipDecorator) user).updateTime());
    }

}
