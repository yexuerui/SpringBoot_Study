package com;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.retry.annotation.EnableRetry;

/**
 * 改注解扫描当前包以及子包
 */
//@MapperScan("com.winter.mapper")
@SpringBootApplication
@EnableRetry //开启retry重试机制
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class,args);
    }
}
