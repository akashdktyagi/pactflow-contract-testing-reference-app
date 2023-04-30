package com.automationfraternity;

import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {
    Employee employee1 = Employee.builder().withId(1).withEmpId(1).withName("Akash").withAge(56).withEmail("a@a.com").withDepartment("HR").withDesignation("recruiter").withPhone("34535").withSalary("100").build();
    Employee employee2 = Employee.builder().withId(2).withEmpId(2).withName("Amit").withAge(56).withEmail("b@b.com").withDepartment("IT").withDesignation("manager").withPhone("34535").withSalary("200").build();
    Employee employee3 = Employee.builder().withId(3).withEmpId(3).withName("Sumit").withAge(56).withEmail("s@s.com").withDepartment("finance").withDesignation("director").withPhone("34535").withSalary("300").build();

    public List<Employee> getAllEmployee(){
        List<Employee> listOfEmployee = new ArrayList<>();
        listOfEmployee.add(employee1);
        listOfEmployee.add(employee2);
        listOfEmployee.add(employee3);
        return listOfEmployee;
    }

    public Employee getEmployeeWithEmpID(String empID){
        if (empID.equalsIgnoreCase("1"))
            return employee1;
        else if (empID.equalsIgnoreCase("2")) {
            return employee2;
        }else if (empID.equalsIgnoreCase("3")) {
            return employee3;
        }else{
            return null;
        }
    }
}
