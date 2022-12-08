package com.automationfraternity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit5.WireMockRuntimeInfo;
import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import java.util.Arrays;
import java.util.List;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;
import static com.github.tomakehurst.wiremock.client.WireMock.*;

@WireMockTest(httpPort = 8083)
class EmployeeServiceTest {

    EmployeeService employeeService;
    RestTemplate restTemplate;

    @BeforeEach
    public void setUp(WireMockRuntimeInfo wireMockRuntimeInfo){
        restTemplate = new RestTemplateBuilder().rootUri(wireMockRuntimeInfo.getHttpBaseUrl()).build();
        employeeService = new EmployeeService(restTemplate);
    }

    @Test
    public void test_get_list_of_all_employees(WireMockRuntimeInfo wireMockRuntimeInfo){
        WireMock wireMock = wireMockRuntimeInfo.getWireMock();
        wireMock.register(get((urlPathEqualTo("/employee")))
                .willReturn(aResponse()
                .withStatus(200)
                .withHeader("Content-Type", "application/json")
                .withBody("[{\"id\":12,\"empId\":1,\"name\":\"Akash\",\"age\":56,\"email\":\"a@a.com\",\"phone\":\"2324235\",\"department\":\"HR\",\"salary\":\"15000\",\"designation\":\"recruiter\"},{\"id\":13,\"empId\":2,\"name\":\"Amit\",\"age\":56,\"email\":\"b@b.com\",\"phone\":\"2324235\",\"department\":\"IT\",\"salary\":\"10000\",\"designation\":\"manager\"},{\"id\":14,\"empId\":3,\"name\":\"Sumit\",\"age\":56,\"email\":\"s@s.com\",\"phone\":\"2324235\",\"department\":\"finance\",\"salary\":\"20000\",\"designation\":\"director\"}]")
        ));

        Employee employee = Employee.builder().withId(12).withEmpId(1).withName("Akash").withAge(56).withEmail("a@a.com").withDepartment("HR").withDesignation("recruiter").withPhone("2324235").withSalary("15000").build();
        Employee employee1 = Employee.builder().withId(13).withEmpId(2).withName("Amit").withAge(56).withEmail("b@b.com").withDepartment("IT").withDesignation("manager").withPhone("2324235").withSalary("10000").build();
        Employee employee2 = Employee.builder().withId(14).withEmpId(3).withName("Sumit").withAge(56).withEmail("s@s.com").withDepartment("finance").withDesignation("director").withPhone("2324235").withSalary("20000").build();
        List<Employee> employeeListExpected = Arrays.asList(employee,employee1,employee2);
        List<Employee> employeeListActual = Arrays.asList(employeeService.getListOfEmployees().getBody());
        Assertions.assertThat(employeeListExpected).isEqualTo(employeeListActual);
    }

    @Test
    public void test_get_employee_by_emp_id(WireMockRuntimeInfo wireMockRuntimeInfo) throws JsonProcessingException {
        Employee employee = Employee.builder().withId(1).withEmpId(1).withName("Akash").withAge(56).withEmail("a@a.com").withDepartment("HR").withDesignation("recruiter").withPhone("2324235").withSalary("15000").build();
        ObjectMapper objectMapper = new ObjectMapper();
        String body = objectMapper.writeValueAsString(employee);

        WireMock wireMock  = wireMockRuntimeInfo.getWireMock();
        wireMock.register(get((urlPathEqualTo("/employee/1")))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(body)
                ));
        ResponseEntity<Employee> responseEntity = employeeService.getEmployeeByEmpID("1");
        Employee employeeActual = (Employee) responseEntity.getBody();
        Assertions.assertThat(employee).isEqualTo(employeeActual);
    }

    @Test
    public void test_employee_by_id_return_404_if_not_found(WireMockRuntimeInfo wireMockRuntimeInfo) throws JsonProcessingException {
        WireMock wireMock  = wireMockRuntimeInfo.getWireMock();
        wireMock.register(get((urlPathEqualTo("/employee/9999")))
                .willReturn(aResponse()
                        .withStatus(404)
                ));
        ResponseEntity<Employee> responseEntity = employeeService.getEmployeeByEmpID("9999");
        Assertions.assertThat(responseEntity.getStatusCodeValue()).isEqualTo(404);
    }
}