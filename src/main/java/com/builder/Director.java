package com.builder;

//导演者角色
public class Director {

    Builder builder;

    public Director(Builder builder) {
        this.builder = builder;
    }

    //调用各个零件的建造方法
    public void construct(String toAddress, String fromAdress) {
        builder.buildToAddress(toAddress);
        builder.buildFromAddress(fromAdress);
        builder.buildSubject();
        builder.buildBody();
        builder.buildSendData();
    }
}
