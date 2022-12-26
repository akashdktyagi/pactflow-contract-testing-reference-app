package com.automationfraternity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployerRestController {

    @Autowired
    EmployerService employeeService;

    @GetMapping("/employee")
    public ResponseEntity<Employee[]> getAllEmployee() {
        return employeeService.getListOfEmployees();
    }

    @GetMapping("/employee/{empId}")
    public ResponseEntity<Employee> getEmployeeByEmpId(String empId) {
        return employeeService.getEmployeeByEmpID(empId);
    }
}
