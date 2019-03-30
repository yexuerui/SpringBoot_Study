package com.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MyInvocationHandler implements InvocationHandler {
    //目标对象
    private Object target;
    //构造方法
    public MyInvocationHandler(Object target) {
        this.target = target;
    }
    /**
     * @param proxy  代理对象
     * @param method 代理对象的某个方法
     * @param args   调用真实方法对象传递的参数
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("权限验证");
        System.out.println("代理对象proxy:"+proxy.getClass());
        //执行target里面的方法
        Object invoke = method.invoke(target, args);
        System.out.println("method的方法类型:" + method);
        for (Object obj : args) {
            System.out.println(obj + " --参数类型--");
        }
        //返回是的代理对象（对目标对象加强的方法）
        System.out.println("日志记录");
        return invoke;
    }
}
