package com.builder;

public class GoodbyeBuilder extends Builder {

    public GoodbyeBuilder() {
        message=new GoodbyeMessage();
    }

    @Override
    public void buildSubject() {
        message.setSubject("欢送标题");
    }

    @Override
    public void buildBody() {
        message.setBody("欢送内容");
    }

    @Override
    public AutoMessage getAutoMessage() {
        return message;
    }
}
