package pl.pjatk.employeemanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.pjatk.employeemanager.model.Employee;
import pl.pjatk.employeemanager.service.EmployeeServiceImpl;

import java.util.List;

@Controller
public class EmployeeController {

    private final EmployeeServiceImpl employeeService;

    @Autowired
    public EmployeeController(EmployeeServiceImpl employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/")
    public String viewHomePage(Model model){
        return findPaginated(1, "firstName","asc",model);
    }

    @GetMapping("/showNewEmployeeForm")
    public String showNewEmployeeForm(Model model){
        Employee employee = new Employee();
        model.addAttribute("employee",employee);
        return "new_employee";
    }

    @PostMapping("/saveEmployee")
    public String saveEmployee(@ModelAttribute("employee") Employee employee){
        employeeService.saveEmployee(employee);
        return "redirect:/";
    }

    @GetMapping("/showFormForEdit/{id}")
    public String showFormForEdit(@PathVariable(value = "id") long id,Model model){
        Employee employee = employeeService.getEmployeeById(id);

        model.addAttribute("employee",employee);
        return "edit_employee";
    }


    @GetMapping("/removeEmployee/{id}")
    public String removeEmployee(@PathVariable(value = "id") long id){
        this.employeeService.removeEmployeeById(id);

        return "redirect:/";
    }

    @GetMapping("/page/{pageNumber}")
    public String findPaginated(@PathVariable(value = "pageNumber") int pageNumber,
                                @RequestParam("sortField") String sortField,
                                @RequestParam("sortDirection") String sortDirection,
                                Model model){
        int pageSize = 4;

        Page<Employee> page = employeeService.findPaginated(pageNumber, pageSize, sortField, sortDirection);
        List<Employee> listEmployees = page.getContent();

        model.addAttribute("currentPage",pageNumber);
        model.addAttribute("totalPages",page.getTotalPages());
        model.addAttribute("totalItems",page.getTotalElements());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDirection", sortDirection);
        model.addAttribute("reverseSortDirection", sortDirection.equals("asc") ? "desc" : "asc");

        model.addAttribute("listEmployees",listEmployees);
        return "index";
    }

    @GetMapping("/403")
    public String error403(){
        return "403";
    }

}
