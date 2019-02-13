package com.yxr.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * SpringBoot整合JSP
 */
@Controller
public class TestJspController {

    @RequestMapping("/testJsp")
    public String jSPIndex() {
        return "success";
    }
}
