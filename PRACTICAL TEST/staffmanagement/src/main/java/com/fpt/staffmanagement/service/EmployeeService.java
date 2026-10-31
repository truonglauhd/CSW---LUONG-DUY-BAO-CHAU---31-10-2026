
package com.fpt.staffmanagement.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.fpt.staffmanagement.model.Employee;
import com.fpt.staffmanagement.repository.EmployeeRepository;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    // 1. Retrieve all employees in an array
    public Employee[] getEmployees() {

        List<Employee> employees = employeeRepository.findAll();

        return employees.toArray(new Employee[0]);
    }

    // 2. Add a new employee
    public Employee addEmployees(Employee employee) {

        // Automatically generate a new ID
        employee.setId(0);

        return employeeRepository.save(employee);
    }

    // 3. Update an existing employee
    @Transactional
    public Employee updateEmployee(Employee employee) {

        if (employee.getId() <= 0) {
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "A valid employee ID is required"
            );
        }

        if (!employeeRepository.existsById(employee.getId())) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Employee not found"
            );
        }

        return employeeRepository.save(employee);
    }
}
