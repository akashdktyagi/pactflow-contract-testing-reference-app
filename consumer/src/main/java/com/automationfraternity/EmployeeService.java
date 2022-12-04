package com.automationfraternity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class EmployeeService {

    RestTemplate restTemplate;

    @Autowired
    public EmployeeService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ResponseEntity<Employee[]> getListOfEmployees(){
        return restTemplate.getForEntity("/employee",Employee[].class);
    }

    public ResponseEntity<Employee> getEmployeeByEmpID(String empID){
        return restTemplate.getForEntity("/employee/"+empID,Employee.class);
    }

}
