package com.automationfraternity.unittests;

import com.automationfraternity.Employee;
import com.automationfraternity.EmployeeService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

    @Test
    public void testGetAllEmployee(){
        EmployeeService employeeService = new EmployeeService();
        List<Employee> employeeList = employeeService.getAllEmployee();
        Assertions.assertThat(employeeList).isInstanceOf(List.class);
    }

    @Test
    public void testGetEmployeeWithEmpIDAs1(){
        EmployeeService employeeService = new EmployeeService();
        Employee employee = employeeService.getEmployeeWithEmpID("1");
        Assertions.assertThat(employee).isInstanceOf(Employee.class);
        Assertions.assertThat(employee.getEmpId()).isEqualTo(1);
    }

    @Test
    public void testGetEmployeeWithEmpIDAs1AndSalaryDataTypeAs(){
        EmployeeService employeeService = new EmployeeService();
        Employee employee = employeeService.getEmployeeWithEmpID("1");
        Assertions.assertThat(employee).isInstanceOf(Employee.class);
        Assertions.assertThat(employee.getSalary()).isInstanceOf(String.class);
    }

    @Test
    public void testGetEmployeeWithEmpIDAs2(){
        EmployeeService employeeService = new EmployeeService();
        Employee employee = employeeService.getEmployeeWithEmpID("2");
        Assertions.assertThat(employee).isInstanceOf(Employee.class);
        Assertions.assertThat(employee.getEmpId()).isEqualTo(2);
    }

    @Test
    public void testGetEmployeeWithEmpIDAs3(){
        EmployeeService employeeService = new EmployeeService();
        Employee employee = employeeService.getEmployeeWithEmpID("3");
        Assertions.assertThat(employee).isInstanceOf(Employee.class);
        Assertions.assertThat(employee.getEmpId()).isEqualTo(3);
    }

    @Test
    public void testGetEmployeeWithEmpIdAsNotFound(){
        EmployeeService employeeService = new EmployeeService();
        Employee employee = employeeService.getEmployeeWithEmpID("99999");
        Assertions.assertThat(employee).isNull();
    }

}
