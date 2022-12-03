package com.automationfraternity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.ArrayList;
import java.util.List;

@Controller
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @GetMapping("/employee")
    public String getAllEmployee(Model model) {
        model.addAttribute("employees", employeeService.getListOfEmployees());
        return "employee";
    }

//    public List<Employee> getAllEmployee(){
//        Employee employee = Employee.builder().withEmpId(1).withName("Akash").build();
//        Employee employee1 = Employee.builder().withEmpId(2).withName("Amit").build();
//        Employee employee2 = Employee.builder().withEmpId(3).withName("Sumit").build();
//        List<Employee> listOfEmployee = new ArrayList<>();
//        listOfEmployee.add(employee);
//        listOfEmployee.add(employee1);
//        listOfEmployee.add(employee2);
//        return listOfEmployee;
//    }
}
