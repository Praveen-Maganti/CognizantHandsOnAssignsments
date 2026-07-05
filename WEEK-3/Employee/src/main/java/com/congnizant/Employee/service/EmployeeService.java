package com.congnizant.Employee.service;

import com.congnizant.Employee.dao.EmployeeDao;
import com.congnizant.Employee.exception.EmployeeNotFoundException;
import com.congnizant.Employee.model.Employee;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeDao employeeDao;

    @Transactional
    public ArrayList<Employee> getAllEmployees(){
        return employeeDao.getAllEmployees();
    }

    public void updateEmployee(Employee employee)
            throws EmployeeNotFoundException {

        employeeDao.updateEmployee(employee);
    }

    public void deleteEmployee(int id)
            throws EmployeeNotFoundException {

        employeeDao.deleteEmployee(id);
    }

}
