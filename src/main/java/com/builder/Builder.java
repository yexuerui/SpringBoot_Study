package com.builder;


import java.time.LocalDate;

//抽象建造者角色
public abstract class Builder {

    //父类的message对象
    protected AutoMessage message;

    //子类定制信息
    public abstract void buildSubject();

    //子类定制信息
    public abstract void buildBody();

    //产品返回方法
    public abstract AutoMessage getAutoMessage();

    //发件地址--建造方法
    public void buildToAddress(String toAddress) {
        message.setToAddress(toAddress);
    }

    //收件地址 --建造方法
    public void buildFromAddress(String fromAddress) {
        message.setFromAddress(fromAddress);
    }

    //发送时间 -- 建造方法
    public void buildSendData() {
        message.setSendData(LocalDate.now());
    }

}
