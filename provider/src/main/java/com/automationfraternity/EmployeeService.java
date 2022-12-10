package com.automationfraternity;

import com.google.common.collect.Lists;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {

    public List<Employee> getAllEmployee(){
        Employee employee = Employee.builder().withId(12).withEmpId(1).withName("Akash").withAge(56).withEmail("a@a.com").withDepartment("HR").withDesignation("recruiter").withPhone("34535").withModeOfCommunication(ModeOfCommunication.builder().withMobile("1234").withPhone("34535").withHomePhone("34535").build()).withSalary("15000").build();
        Employee employee1 = Employee.builder().withId(13).withEmpId(2).withName("Amit").withAge(56).withEmail("b@b.com").withDepartment("IT").withDesignation("manager").withPhone("34535").withModeOfCommunication(ModeOfCommunication.builder().withMobile("1234").withPhone("34535").withHomePhone("34535").build()).withSalary("10000").build();
        Employee employee2 = Employee.builder().withId(14).withEmpId(3).withName("Sumit").withAge(56).withEmail("s@s.com").withDepartment("finance").withDesignation("director").withPhone("34535").withModeOfCommunication(ModeOfCommunication.builder().withMobile("1234").withPhone("34535").withHomePhone("34535").build()).withSalary("20000").build();
        List<Employee> listOfEmployee = new ArrayList<>();
        listOfEmployee.add(employee);
        listOfEmployee.add(employee1);
        listOfEmployee.add(employee2);
        return listOfEmployee;
    }

    public Employee getEmployeeWithEmpID(String empID){
        if (empID.equalsIgnoreCase("1"))
            return Employee.builder().withId(1).withEmpId(1).withName("Akash").withAge(56).withEmail("a@a.com").withDepartment("HR").withDesignation("recruiter").withPhone("12345").withModeOfCommunication(ModeOfCommunication.builder().withMobile("1234").withPhone("34535").withHomePhone("34535").build()).withSalary("15000").build();
        else if (empID.equalsIgnoreCase("2")) {
            return Employee.builder().withId(2).withEmpId(2).withName("Amit").withAge(56).withEmail("a@a.com").withDepartment("HR").withDesignation("recruiter").withPhone("12345").withModeOfCommunication(ModeOfCommunication.builder().withMobile("1234").withPhone("34535").withHomePhone("34535").build()).withSalary("15000").build();
        }else if (empID.equalsIgnoreCase("10")) {
            return Employee.builder().withId(2).withEmpId(10).withName("Sumit").withAge(56).withEmail("a@a.com").withDepartment("HR").withDesignation("recruiter").withPhone("12345").withModeOfCommunication(ModeOfCommunication.builder().withMobile("1234").withPhone("34535").withHomePhone("34535").build()).withSalary("15000").build();
        }else{
         return null;
        }
    }
}
