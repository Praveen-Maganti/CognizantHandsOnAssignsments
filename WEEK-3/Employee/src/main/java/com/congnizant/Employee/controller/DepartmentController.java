package com.congnizant.Employee.controller;

import com.congnizant.Employee.model.Department;
import com.congnizant.Employee.service.DepartmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;
    private static final Logger LOGGER= LoggerFactory.getLogger(DepartmentController.class);
    @GetMapping("/departments")
    public ArrayList<Department> getAllDepartments(){
        LOGGER.debug("Department Controller is called");
        return departmentService.getAllDepartments();
    }
}
