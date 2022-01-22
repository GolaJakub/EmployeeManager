package pl.pjatk.employeemanager.service;

import org.springframework.data.domain.Page;
import pl.pjatk.employeemanager.model.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> getAllEmployees();
    void saveEmployee(Employee employee);
    Employee getEmployeeById(long id);
    void removeEmployeeById(long id);
    Page<Employee> findPaginated(int pageNumber, int pageSize, String sortField, String sortDirection);
}
