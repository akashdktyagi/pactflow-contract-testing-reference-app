package com.automationfraternity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class EmployeeService {

    RestTemplate restTemplate;
    
    @Value("${emp_provider.root}")
    String consumerApiUrl;

    @Autowired
    public EmployeeService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Employee[] getListOfEmployees(){
        ResponseEntity<Employee[]>  responseEntity = restTemplate.getForEntity(consumerApiUrl + "/employee",Employee[].class);
        return responseEntity.getBody();
//        restTemplate.exchange("/employee", HttpMethod.GET,new ParameterizedTypeReference<List<Employee>(){})
    }

}
