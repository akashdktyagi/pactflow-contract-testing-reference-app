package com.automationfraternity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * This class is only to display results in HTML
 */
@Controller
public class EmployerController {

    @Autowired
    EmployerService employeeService;

    @GetMapping("/employee/ui")
    public String getAllEmployee(Model model) {
        model.addAttribute("employees", employeeService.getListOfEmployees());
        return "employee";
    }
}
