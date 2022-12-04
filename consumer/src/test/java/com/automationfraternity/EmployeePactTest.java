package com.automationfraternity;


import au.com.dius.pact.consumer.MockServer;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.consumer.junit5.PactConsumerTestExt;
import au.com.dius.pact.consumer.junit5.PactTestFor;
import au.com.dius.pact.core.model.RequestResponsePact;
import au.com.dius.pact.core.model.annotations.Pact;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit5.WireMockRuntimeInfo;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static au.com.dius.pact.consumer.dsl.LambdaDsl.newJsonArrayMinLike;
import static au.com.dius.pact.consumer.dsl.LambdaDsl.newJsonBody;
import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(PactConsumerTestExt.class)
public class EmployeePactTest {

    private Map<String, String> headers() {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json; charset=utf-8");
        return headers;
    }

    @Pact(consumer = "FrontendApp", provider = "EmployeeServiceAPI")
    public RequestResponsePact getAllEmployee(PactDslWithProvider builder){
        return builder.given("employee exists")
                .uponReceiving("get all employees")
                .method("GET")
                .path("/employee")
                .willRespondWith()
                .status(200)
                .headers(headers())
                .body(newJsonArrayMinLike(2, array ->
                        array.object(object -> {
                            object.stringType("age", "78");
                            object.stringType("department", "HR");
                            object.stringType("designation", "Manager");
                            object.stringType("email", "a@a.com");
                            object.stringType("empId", "123456");
                            object.stringType("id", "123");
                            object.stringType("name", "Akash");
                            object.stringType("phone", "342424");
                            object.stringType("salary", "5000");
                        })).build())
                .toPact();
    }

    @Pact(consumer = "FrontendApp", provider = "EmployeeServiceAPI")
    @Disabled
    public RequestResponsePact return404IfEmpIdNotFound(PactDslWithProvider builder){
        return builder.given("Employee GET: the employee does not exist" )
                .uponReceiving("get employee by emp id")
                .method("GET")
                .path("/employee/9999")
                .willRespondWith()
                .status(404)
                .toPact();
    }

    @Pact(consumer = "FrontendApp", provider = "EmployeeServiceAPI")
    @Disabled
    public RequestResponsePact getEmployeeByEmpID(PactDslWithProvider builder){
        return builder.given("employee exists")
                .uponReceiving("get employee by emp id")
                .method("GET")
                .path(String.format("/address/%s", 10))
                .willRespondWith()
                .status(200)
                .headers(headers())
                .body(newJsonBody((o) -> {
                            o.stringType("age", "78");
                            o.stringType("department", "HR");
                            o.stringType("designation", "Manager");
                            o.stringType("email", "a@a.com");
                            o.stringType("empId", "10");
                            o.stringType("id", "123");
                            o.stringType("name", "Akash");
                            o.stringType("phone", "342424");
                            o.stringType("salary", "5000");
                        }).build())
                .toPact();
    }

    @Test
    @PactTestFor(pactMethod = "getAllEmployee")
    public void testGetAllEmployee(MockServer mockServer){
        RestTemplate restTemplate = new RestTemplateBuilder().rootUri(mockServer.getUrl()).build();
        EmployeeService employeeService = new EmployeeService(restTemplate);

        Employee employee = Employee.builder()
                .withId(123)
                .withEmpId(123456)
                .withName("Akash")
                .withAge(78)
                .withEmail("a@a.com")
                .withDepartment("HR")
                .withDesignation("Manager")
                .withPhone("342424")
                .withSalary("5000").build();
        List<Employee> employeeListExpected = Arrays.asList(employee,employee);

        ResponseEntity<Employee[]> responseEntity = employeeService.getListOfEmployees();
        Assertions.assertThat(employeeListExpected).isEqualTo(Arrays.asList(responseEntity.getBody()));
        Assertions.assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
    }

    @Test
    @Disabled
    @PactTestFor(pactMethod = "getEmployeeByEmpID")
    public void testGetEmployeeByEmpID(MockServer mockServer){
        RestTemplate restTemplate = new RestTemplateBuilder().rootUri(mockServer.getUrl()).build();
        EmployeeService employeeService = new EmployeeService(restTemplate);
        Employee employee = Employee.builder()
                .withId(123)
                .withEmpId(10)
                .withName("Akash")
                .withAge(78)
                .withEmail("a@a.com")
                .withDepartment("HR")
                .withDesignation("Manager")
                .withPhone("342424")
                .withSalary("5000").build();

        ResponseEntity<Employee> responseEntity = employeeService.getEmployeeByEmpID("10");
        Assertions.assertThat(responseEntity.getBody()).isEqualTo(employee);
        Assertions.assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
    }

    @Test
    @Disabled
    @PactTestFor(pactMethod = "return404IfEmpIdNotFound")
    public void testReturn404WhenEmployeeIDNotFound(MockServer mockServer){
        RestTemplate restTemplate = new RestTemplateBuilder().rootUri(mockServer.getUrl()).build();
        EmployeeService employeeService = new EmployeeService(restTemplate);
        ResponseEntity<Employee> responseEntity = employeeService.getEmployeeByEmpID("9999");
        Assertions.assertThat(responseEntity.getStatusCodeValue()).isEqualTo(404);
    }

}
