package com.automationfraternity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @GetMapping("/employee")
    public List<Employee> getAllEmployee(){
        return employeeService.getAllEmployee();
    }

    @GetMapping("/employee/{empId}")
    public ResponseEntity<String> getEmployeeWithEmployeeId(@PathVariable String empId) throws JsonProcessingException {
        Employee employee = employeeService.getEmployeeWithEmpID(empId);
        if (employee==null){
            return new ResponseEntity("Emp Id not found: " + empId,HttpStatus.NOT_FOUND);
        }else{
            ObjectMapper objectMapper = new ObjectMapper();
            String body = objectMapper.writeValueAsString(employee);
            return new ResponseEntity(body,HttpStatus.OK);
        }
    }
}
