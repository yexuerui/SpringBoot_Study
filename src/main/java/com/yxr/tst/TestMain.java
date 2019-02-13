package com.yxr.tst;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
//是因为缺少ServletWebServerFactory bean

@ComponentScan(basePackages = "com.yxr.controller")
@EnableAutoConfiguration
public class TestMain {
    public static void main(String[] args) {
        SpringApplication.run(TestMain.class,args);
    }
}
