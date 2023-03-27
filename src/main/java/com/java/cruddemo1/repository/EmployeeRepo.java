package com.java.cruddemo1.repository;

import com.java.cruddemo1.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepo extends JpaRepository<Employee, Long> {
}
