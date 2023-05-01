package com.automationfraternity.unittests;

import com.automationfraternity.Employee;
import com.automationfraternity.EmployeeController;
import com.automationfraternity.EmployeeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import java.util.List;

@ExtendWith(MockitoExtension.class)
public class EmployeeControllerTest {

    @Mock
    EmployeeService employeeService;
    @InjectMocks
    EmployeeController employeeController;

    @Test
    public void testGetAllEmployee() throws JsonProcessingException {
        Assertions.assertThat(employeeController.getAllEmployee()).isInstanceOf(ResponseEntity.class);
    }

    @Test
    public void testGetBEmployeeById() throws JsonProcessingException {
        Assertions.assertThat(employeeController.getEmployeeWithEmployeeId("1")).isInstanceOf(ResponseEntity.class);
    }
}
