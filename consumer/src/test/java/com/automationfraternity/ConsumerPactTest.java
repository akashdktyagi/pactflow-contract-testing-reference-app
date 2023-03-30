package com.automationfraternity;

import au.com.dius.pact.consumer.MockServer;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.consumer.junit5.PactConsumerTestExt;
import au.com.dius.pact.consumer.junit5.PactTestFor;
import au.com.dius.pact.core.model.RequestResponsePact;
import au.com.dius.pact.core.model.annotations.Pact;
import org.assertj.core.api.Assertions;
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

@ExtendWith(PactConsumerTestExt.class)
public class ConsumerPactTest {

    private Map<String, String> headers() {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json; charset=utf-8");
        return headers;
    }

    @Pact(consumer = "EmployerServiceAPI", provider = "EmployeeServiceAPI")
    public RequestResponsePact GeneratePactFor_GetAllEmployee(PactDslWithProvider builder){
        return builder.given("some employee exists")
                .uponReceiving("get all employees")
                .method("GET")
                .path("/employee")
                .willRespondWith()
                .status(200)
                .headers(headers())
                .body(newJsonArrayMinLike(2, array ->
                        array.object(o -> {
                            o.integerType("age", 78);
                            o.stringType("department", "HR");
                            o.stringType("designation", "Manager");
                            o.stringType("email", "a@a.com");
                            o.integerType("empId", 1);
                            o.integerType("id", 1);
                            o.stringType("name", "Akash");
                            o.stringType("phone", "342424");
                            o.stringType("salary", "100");
                        })).build())
                .toPact();
    }

    @Pact(consumer = "EmployerServiceAPI", provider = "EmployeeServiceAPI")
    public RequestResponsePact GeneratePactFor_ReturnErrorIfEmpIdNotFoundPact(PactDslWithProvider builder){
        return builder.given("employee id does not exist" )
                .uponReceiving("get employee by emp id which does not exist")
                .method("GET")
                .path("/employee/9999")
                .willRespondWith()
                .status(404)
                .toPact();
    }

    @Pact(consumer = "EmployerServiceAPI", provider = "EmployeeServiceAPI")
    public RequestResponsePact GeneratePactFor_GetEmployeeByEmpID(PactDslWithProvider builder){
        return builder.given("emp id exists")
                .uponReceiving("get employee by emp id which exists")
                .method("GET")
                .path(String.format("/employee/%s", 1))
                .willRespondWith()
                .status(200)
                .headers(headers())
                .body(newJsonBody((o) -> {
                            o.integerType("age", 78);
                            o.stringType("department", "HR");
                            o.stringType("designation", "Manager");
                            o.stringType("email", "a@a.com");
                            o.integerType("empId", 1);
                            o.integerType("id", 1);
                            o.stringType("name", "Akash");
                            o.stringType("phone", "342424");
                            o.stringType("salary", "100");
                        }).build())
                .toPact();
    }

    @Test
    @PactTestFor(pactMethod = "GeneratePactFor_GetAllEmployee")
    public void testGetAllEmployee(MockServer mockServer){
        RestTemplate restTemplate = new RestTemplateBuilder().rootUri(mockServer.getUrl()).build();
        EmployerService employeeService = new EmployerService(restTemplate);

        Employee employee = Employee.builder()
                .withId(1)
                .withEmpId(1)
                .withName("Akash")
                .withAge(78)
                .withEmail("a@a.com")
                .withDepartment("HR")
                .withDesignation("Manager")
                .withPhone("342424")
                .withSalary("100").build();
        List<Employee> employeeListExpected = Arrays.asList(employee,employee);

        ResponseEntity<Employee[]> responseEntity = employeeService.getListOfEmployees();
        Assertions.assertThat(employeeListExpected).isEqualTo(Arrays.asList(responseEntity.getBody()));
        Assertions.assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
    }

    @Test
    @PactTestFor(pactMethod = "GeneratePactFor_GetEmployeeByEmpID")
    public void testGetEmployeeByEmpID(MockServer mockServer){
        RestTemplate restTemplate = new RestTemplateBuilder().rootUri(mockServer.getUrl()).build();
        EmployerService employeeService = new EmployerService(restTemplate);
        Employee employee = Employee.builder()
                .withId(1)
                .withEmpId(1)
                .withName("Akash")
                .withAge(78)
                .withEmail("a@a.com")
                .withDepartment("HR")
                .withDesignation("Manager")
                .withPhone("342424")
                .withSalary("100").build();

        ResponseEntity<Employee> responseEntity = employeeService.getEmployeeByEmpID("1");
        Assertions.assertThat(responseEntity.getBody()).isEqualTo(employee);
        Assertions.assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
    }

    @Test
    @PactTestFor(pactMethod = "GeneratePactFor_ReturnErrorIfEmpIdNotFoundPact")
    public void testReturnError_WhenEmployeeIDNotFound(MockServer mockServer){
        RestTemplate restTemplate = new RestTemplateBuilder().rootUri(mockServer.getUrl()).build();
        EmployerService employeeService = new EmployerService(restTemplate);
        ResponseEntity<Employee> responseEntity = employeeService.getEmployeeByEmpID("9999");
        Assertions.assertThat(responseEntity.getStatusCodeValue()).isEqualTo(400);
    }
}
