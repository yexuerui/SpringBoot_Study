package com.test;

public class TestFinallly {

    public static int test(int x, int y) {
        int result = x;
        try {
            result = x + y;
            System.out.println("try中的结果："+result);
            //返回的基本数据类型，在执行finally中，其实是值传递
            return result;
        } finally {
            result = x - y;
            System.out.println("result中的结果："+result);
        }
    }

    public static void main(String[] args) {
        int x = 3;
        int y = 5;
        int result = test(5, 3);
        System.out.println(result);
    }

}
