package com.builder;

//具体建造者角色
public class WelcomeBuilder extends Builder  {

    public WelcomeBuilder() {
        message=new WelcomeMessage();
    }

    @Override
    public void buildSubject() {
        message.setSubject("欢迎标题");
    }

    @Override
    public void buildBody() {
        message.setBody("欢迎内容");
    }

    @Override
    public AutoMessage getAutoMessage() {
        return message;
    }
}
