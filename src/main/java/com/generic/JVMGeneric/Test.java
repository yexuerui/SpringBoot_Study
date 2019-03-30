package com.generic.JVMGeneric;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

//类型擦除泛型类
public class Test<T> {
    private T data;

    public boolean isBoolean(List<T> data) {

//        data.wait();

        return true;
    }

    //查看反编译文件
    public static void main(String[] args) throws NoSuchMethodException {
        Test test = new Test();
        List<String> list = new ArrayList<String>() {};
        test.isBoolean(list);
    }
}
