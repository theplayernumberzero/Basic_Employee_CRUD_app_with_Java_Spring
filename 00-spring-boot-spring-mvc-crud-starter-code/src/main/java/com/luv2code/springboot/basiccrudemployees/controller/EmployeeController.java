package com.luv2code.springboot.basiccrudemployees.controller;

import com.luv2code.springboot.basiccrudemployees.entity.Employee;
import com.luv2code.springboot.basiccrudemployees.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }

    @GetMapping("/")
    public String rootRedirect(){
        return "redirect:employees/list";
    }

    @GetMapping("/list")
    public String listEmployees(Model theModel){
        //get employees from db
        List<Employee> employees = employeeService.findAll();
        //add to the spring model
        theModel.addAttribute("employees" ,employees);

        return "list-employees";
    }
}
