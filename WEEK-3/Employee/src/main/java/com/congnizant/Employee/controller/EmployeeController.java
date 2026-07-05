package com.congnizant.Employee.controller;

import com.congnizant.Employee.exception.EmployeeNotFoundException;
import com.congnizant.Employee.model.Employee;
import com.congnizant.Employee.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employees")
    public ArrayList<Employee> getAllEmployees(){
        return employeeService.getAllEmployees();
    }

    @PutMapping("/employees")
    public void updateEmployee(
            @RequestBody @Valid Employee employee)
            throws EmployeeNotFoundException {

        employeeService.updateEmployee(employee);
    }

    @DeleteMapping("/employees/{id}")
    public void deleteEmployee(
            @PathVariable int id)
            throws EmployeeNotFoundException {

        employeeService.deleteEmployee(id);
    }
}
