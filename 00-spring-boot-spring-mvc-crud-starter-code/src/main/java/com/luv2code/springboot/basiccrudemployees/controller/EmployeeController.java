package com.luv2code.springboot.basiccrudemployees.controller;

import com.luv2code.springboot.basiccrudemployees.entity.Employee;
import com.luv2code.springboot.basiccrudemployees.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

        return "employees/list-employees";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel){
        //create model attribute for data binding
        Employee theEmployee = new Employee();

        theModel.addAttribute("employee", theEmployee);

        return "employees/employee-form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("employee") Employee theEmployee){
        //save employee
        employeeService.save(theEmployee);
        //after save redirect to list page
        return "redirect:/employees/list";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("employeeId") int theId, Model theModel){
        Employee theEmployee = employeeService.findById(theId);

        theModel.addAttribute("employee",theEmployee);

        return "employees/employee-form";
    }

    //HTML DELETE requesti desteklemediği için html içinde direk kullanamazsın.
    @GetMapping("/delete")
    public String delete(@RequestParam("employeeId") int theId, Model theModel) {
        employeeService.deleteById(theId);

        return "redirect:/employees/list";
    }
}
