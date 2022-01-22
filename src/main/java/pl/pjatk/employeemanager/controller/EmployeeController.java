package pl.pjatk.employeemanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pl.pjatk.employeemanager.model.Employee;
import pl.pjatk.employeemanager.service.EmployeeService;

import java.util.List;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/")
    public String viewHomePage(Model model){
        //model.addAttribute("listEmployees",employeeService.getAllEmployees());
        return findPaginated(1, model);
        //return "index";
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
    public String findPaginated(@PathVariable(value = "pageNumber") int pageNumber, Model model){
        int pageSize = 4;

        Page<Employee> page = employeeService.findPaginated(pageNumber, pageSize);
        List<Employee> listEmployees = page.getContent();

        model.addAttribute("currentPage",pageNumber);
        model.addAttribute("totalPages",page.getTotalPages());
        model.addAttribute("totalItems",page.getTotalElements());
        model.addAttribute("listEmployees",listEmployees);
        return "index";
        //return null;
    }

}
