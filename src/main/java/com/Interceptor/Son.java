package com.Interceptor;

import com.JsonSerializer.Father;

//私有构造方法不能被继承
public class Son extends Father{


    public Son(String s) {
        super(s);
    }
}
