package com.ms;

public class Feb {
    private String name="feb";
    public Feb(){
        tellMQ();
        tellRedis();
    }

    public void tellMQ(){
        System.out.println("The Feb MQ name is "+name);
    }
    public void tellRedis(){
        System.out.println("The Feb Redis name is "+name);
    }
}
