package com.ms;

public class Sub extends Feb{
    private String name="sub";
    public Sub(){
        super();
        tellMQ();
        tellRedis();
    }
    public void tellMQ(){
        System.out.println("The Sub MQ name is "+name);
    }
    public void tellRedis(){
        System.out.println("The Sub Redis name is "+name);
    }
    //执行该方法后，会输出什么数据
    public static void main(String[] args) {
        Feb object=new Sub();
        Thread thread=new Thread();
    }
}
