package com.automationfraternity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.*;

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
        try{
            return restTemplate.getForEntity("/employee/{empID}",Employee.class,empID);
        }catch(RestClientException e){
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

    }

}
