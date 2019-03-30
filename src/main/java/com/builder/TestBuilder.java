package com.builder;

//测试方法
public class TestBuilder {

    public static void main(String[] args) {
        Builder builder=new WelcomeBuilder();
        Director director = new Director(builder);
        director.construct("toAddress:123.100.12.11","fromAddress:192.100.12.12");
        //最后的对象是哪个？
        WelcomeMessage autoMessage = (WelcomeMessage) builder.getAutoMessage();
        System.out.println("建造者的消息："+autoMessage);
    }
}
