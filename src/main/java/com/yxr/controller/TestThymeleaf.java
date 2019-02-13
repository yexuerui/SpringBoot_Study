package com.yxr.controller;

import com.yxr.entity.Person;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
public class TestThymeleaf {

    @RequestMapping("thymeleaf")
    public String getThymeleaf(Model model, HttpSession session){
        Person p=new Person();
        p.setName("黎明");
        p.setPassword("123");
        p.setSex("0");
        p.setBirth(new Date());

        ArrayList<Person> people = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Person person=new Person();
            person.setName("黎明"+i);
            person.setSex(i%2+"");
            person.setBirth(new Date());
            people.add(person);
        }
        model.addAttribute("list",people);
        model.addAttribute("p",p);
        session.setAttribute("person",p);
        return "success";
    }

    @RequestMapping("thymeleafTa")
    @ResponseBody
    public String TestModelAttribute(@RequestParam("param") String param){
        System.out.println("P : "+param);
        return param+" is success";
    }

}
