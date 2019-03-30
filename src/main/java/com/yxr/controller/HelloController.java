package com.yxr.controller;

import com.common.Log;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;

@RestController
public class HelloController<getDateFromPost> {

    @RequestMapping("/hello")
    public String index() {
        return "hello world";
    }

    @RequestMapping(value = "/{path}/resource", method = RequestMethod.GET)
    public String index(Map<String, Object> map, @PathVariable("path") String resourcePath, HttpServletRequest request) {
        String contextPath = request.getContextPath();
        System.out.println("服务器路径：" + contextPath);
        System.out.println("路径资源：" + resourcePath);
        resourcePath = "D:\\idea_source\\mySpringBoothelloworld\\src\\main\\resources\\static\\bbb.mp4";
        map.put("path", resourcePath);
        return "index";
    }

    @RequestMapping("/format")
    public void getDateFromPost(@RequestParam("d") Date date){
        System.out.println("date : "+date);
    }

    @RequestMapping("/testLog")
    @Log(value = "记录的testLog的日志")
    public String testLog(@RequestParam("names") String name){
        return "names"+name;
    }


}
