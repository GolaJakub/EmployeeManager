package pl.pjatk.employeemanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.pjatk.employeemanager.service.EmployeeService;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/")
    public String viewHomePage(Model model){
        model.addAttribute("listEmployees",employeeService.getAllEmployees());
        return "index";
    }
}
