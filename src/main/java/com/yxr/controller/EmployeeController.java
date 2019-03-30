package com.yxr.controller;

import com.winter.model.Employee;
import com.yxr.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService service;

    @RequestMapping("/addEmployee")
    public String addEmployee() {
        Employee employee = new Employee();
        employee.setdId(66);
        employee.setLastName("新剑姬");
        employee.setEmail("jj.163.com");
        employee.setGender("1");
        int addEmployee = service.addEmployee(employee);
        return addEmployee + "处理成功！";
    }

}
