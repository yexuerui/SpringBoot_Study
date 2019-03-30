package com.proxy;

/**
 * 静态代理（定义代理类并实现业务接口）
 * 最后便可以通过客户端进行调用
 */
public class StudentDaoProxy implements StudentDao {

    //被代理的对象
    private StudentDao target;

    public StudentDaoProxy(StudentDao studentDao) {
        this.target = target;
    }

    @Override
    public void login(String name, String password) {
        //加入扩展代码
        System.out.println("静态代理-权限控制");
        target.login(name, password);  //执行业务代码
        System.out.println("静态代理-日志输出");
    }

    @Override
    public void regist() {

    }
}
