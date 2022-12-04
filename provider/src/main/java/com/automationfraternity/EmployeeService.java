package com.automationfraternity;

import com.google.common.collect.Lists;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {

    public List<Employee> getAllEmployee(){
        Employee employee = Employee.builder().withId(12).withEmpId(1).withName("Akash").withAge(56).withEmail("a@a.com").withDepartment("HR").withDesignation("recruiter").withModeOfCommunication(ModeOfCommunication.builder().withMobile("1234").withPhone("34535").withHomePhone("34535").build()).withSalary("15000").build();
        Employee employee1 = Employee.builder().withId(13).withEmpId(2).withName("Amit").withAge(56).withEmail("b@b.com").withDepartment("IT").withDesignation("manager").withModeOfCommunication(ModeOfCommunication.builder().withMobile("1234").withPhone("34535").withHomePhone("34535").build()).withSalary("10000").build();
        Employee employee2 = Employee.builder().withId(14).withEmpId(3).withName("Sumit").withAge(56).withEmail("s@s.com").withDepartment("finance").withDesignation("director").withModeOfCommunication(ModeOfCommunication.builder().withMobile("1234").withPhone("34535").withHomePhone("34535").build()).withSalary("20000").build();
        List<Employee> listOfEmployee = new ArrayList<>();
        listOfEmployee.add(employee);
        listOfEmployee.add(employee1);
        listOfEmployee.add(employee2);
        return listOfEmployee;
    }
}
