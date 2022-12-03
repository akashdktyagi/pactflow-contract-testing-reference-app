package com.automationfraternity;

import com.google.common.collect.Lists;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {

    public List<Employee> getAllEmployee(){
        Employee employee = Employee.builder().withEmpId(1).withName("Akash").withAge(56).withEmail("a@a.com").withDepartment("HR").withDesignation("recruiter").withPhone("2324235").withSalary("5000").build();
        Employee employee1 = Employee.builder().withEmpId(2).withName("Amit").build();
        Employee employee2 = Employee.builder().withEmpId(3).withName("Sumit").build();
        List<Employee> listOfEmployee = new ArrayList<>();
        listOfEmployee.add(employee);
        listOfEmployee.add(employee1);
        listOfEmployee.add(employee2);
        return listOfEmployee;
    }
}
