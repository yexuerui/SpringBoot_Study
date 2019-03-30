package com.proxy;

public class StudentDaoImpl implements StudentDao {
    @Override
    public void login(String name, String password) {
        if (name == "tom" && password == "123") {
            System.out.println(name + " 登录成功");
        } else {
            System.out.println(name + " 登录失败");
        }
    }
    @Override
    public void regist() {
        System.out.println("注册功能");
    }
}
