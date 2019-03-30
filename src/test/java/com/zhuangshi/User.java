package com.zhuangshi;

//装饰者和被装饰对象有相同的超类型

public class User {
    private String des;

    public User() {
        des=" I am common user";
    }

    public String getDes() {
        return des;
    }
    public void function() {
        System.out.println("I can use common function.");
    }
}
