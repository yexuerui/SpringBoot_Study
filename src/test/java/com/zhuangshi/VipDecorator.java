package com.zhuangshi;

import java.util.Date;

public class VipDecorator  extends  Decorator{

    private Date mDate;
    private User user;

    public VipDecorator(User user) {
        mDate=new Date();
        this.user = user;
    }

    @Override
    public String updateTime() {
        return mDate.toString();
    }

    @Override
    public void function() {
        user.function();
        System.out.println("Vip function");
    }

    @Override
    public String getDes() {
        return "I am vip user.";
    }
}
