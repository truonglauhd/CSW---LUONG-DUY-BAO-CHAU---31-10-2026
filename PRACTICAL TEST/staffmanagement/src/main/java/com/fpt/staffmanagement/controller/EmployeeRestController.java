
package com.fpt.staffmanagement.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import com.fpt.staffmanagement.model.Employee;
import com.fpt.staffmanagement.service.EmployeeService;

@RestController
@RequestMapping("/api/employees")
public class EmployeeRestController {

    private final EmployeeService employeeService;

    public EmployeeRestController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    // 1. GET: Retrieve all employees
    @GetMapping
    public Employee[] getEmployees() {
        return employeeService.getEmployees();
    }

    // 2. POST: Add a new employee
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Employee addEmployees(@Valid @RequestBody Employee employee) {

        return employeeService.addEmployees(employee);
    }

    // 3. PUT: Update an existing employee
    @PutMapping
    public Employee updateEmployee(@Valid @RequestBody Employee employee) {

        return employeeService.updateEmployee(employee);
    }
}
