package com.automationfraternity;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "I Am a Consumer")
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
