package com.automationfraternity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class EmployeeService {

    RestTemplate restTemplate;

    @Autowired
    public EmployeeService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Employee> getListOfEmployees(){
        ResponseEntity<Employee[]>  responseEntity = restTemplate.getForEntity("/employee",Employee[].class);
        return Arrays.asList(responseEntity.getBody());
//        restTemplate.exchange("/employee", HttpMethod.GET,new ParameterizedTypeReference<List<Employee>(){})
    }

}
