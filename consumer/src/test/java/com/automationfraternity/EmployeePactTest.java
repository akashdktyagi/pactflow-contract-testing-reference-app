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
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static au.com.dius.pact.consumer.dsl.LambdaDsl.newJsonArrayMinLike;
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
                .body("[{\"id\":12,\"empId\":1,\"name\":\"Akash\",\"age\":56,\"email\":\"a@a.com\",\"phone\":\"2324235\",\"department\":\"HR\",\"salary\":\"15000\",\"designation\":\"recruiter\"},{\"id\":13,\"empId\":2,\"name\":\"Amit\",\"age\":56,\"email\":\"b@b.com\",\"phone\":\"2324235\",\"department\":\"IT\",\"salary\":\"10000\",\"designation\":\"manager\"},{\"id\":14,\"empId\":3,\"name\":\"Sumit\",\"age\":56,\"email\":\"s@s.com\",\"phone\":\"2324235\",\"department\":\"finance\",\"salary\":\"20000\",\"designation\":\"director\"}]")
//                .build()
                .toPact();
//                .body(newJsonArrayMinLike(2, array ->
//                        array.object(object -> {
//                            object.stringType("id", "09");
//                            object.stringType("type", "CREDIT_CARD");
//                            object.stringType("name", "Gem Visa");
//                        })


    }


    @Test
    @PactTestFor(pactMethod = "getAllEmployee")
    public void test_get_list_of_all_employees(MockServer mockServer){
        RestTemplate restTemplate = new RestTemplateBuilder().rootUri(mockServer.getUrl()).build();
        EmployeeService employeeService = new EmployeeService(restTemplate);

        Employee employee = Employee.builder().withId(12).withEmpId(1).withName("Akash").withAge(56).withEmail("a@a.com").withDepartment("HR").withDesignation("recruiter").withPhone("2324235").withSalary("15000").build();
        Employee employee1 = Employee.builder().withId(13).withEmpId(2).withName("Amit").withAge(56).withEmail("b@b.com").withDepartment("IT").withDesignation("manager").withPhone("2324235").withSalary("10000").build();
        Employee employee2 = Employee.builder().withId(14).withEmpId(3).withName("Sumit").withAge(56).withEmail("s@s.com").withDepartment("finance").withDesignation("director").withPhone("2324235").withSalary("20000").build();
        List<Employee> employeeListExpected = Arrays.asList(employee,employee1,employee2);

        List<Employee> employeeListActual = employeeService.getListOfEmployees();
        Assertions.assertThat(employeeListExpected).isEqualTo(employeeListActual);

    }

}
