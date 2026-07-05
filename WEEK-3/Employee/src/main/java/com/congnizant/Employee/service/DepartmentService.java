package com.congnizant.Employee.service;

import com.congnizant.Employee.dao.DepartmentDao;
import com.congnizant.Employee.model.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentDao departmentDao;

    @Transactional
    public ArrayList<Department> getAllDepartments(){
        return departmentDao.getAllDepartments();
    }
}
