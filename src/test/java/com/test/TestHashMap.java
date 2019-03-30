package com.test;


import java.util.HashMap;

public class TestHashMap {

    public static void main(String[] args) {
        HashMap<String, String> map = new HashMap<>();
        map.put("1","2");
        map.put("1","3");
        System.out.println(map.get("1"));
    }

}
