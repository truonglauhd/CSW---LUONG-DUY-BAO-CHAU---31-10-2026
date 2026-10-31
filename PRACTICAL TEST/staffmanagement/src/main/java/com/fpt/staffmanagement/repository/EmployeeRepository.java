
package com.fpt.staffmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fpt.staffmanagement.model.Employee;

@Repository
public interface EmployeeRepository
        extends JpaRepository<Employee, Integer> {

}
