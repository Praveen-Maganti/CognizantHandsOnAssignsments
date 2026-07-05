package com.congnizant.Employee.dao;

import com.congnizant.Employee.exception.EmployeeNotFoundException;
import com.congnizant.Employee.model.Employee;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public class EmployeeDao {

    private static ArrayList<Employee> EMPLOYEE_LIST;

    public void updateEmployee(Employee employee)
            throws EmployeeNotFoundException {

        for (int i = 0; i < EMPLOYEE_LIST.size(); i++) {

            if (EMPLOYEE_LIST.get(i).getId()==(employee.getId())) {
                EMPLOYEE_LIST.set(i, employee);
                return;
            }
        }

        throw new EmployeeNotFoundException("Employee not found");
    }

    public void deleteEmployee(int id)
            throws EmployeeNotFoundException {

        for (Employee employee : EMPLOYEE_LIST) {

            if (employee.getId()==(id)) {
                EMPLOYEE_LIST.remove(employee);
                return;
            }
        }

        throw new EmployeeNotFoundException("Employee not found");
    }

    public EmployeeDao() {

        ApplicationContext context =
                new ClassPathXmlApplicationContext("employee.xml");

        EMPLOYEE_LIST =
                (ArrayList<Employee>) context.getBean("employeeList");
    }

    public ArrayList<Employee> getAllEmployees() {
        return EMPLOYEE_LIST;
    }
}
