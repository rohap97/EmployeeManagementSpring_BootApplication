package com.java.cruddemo1.service.impl;

import com.java.cruddemo1.exception.ResourceNotFoundException;
import com.java.cruddemo1.model.Employee;
import com.java.cruddemo1.repository.EmployeeRepo;
import com.java.cruddemo1.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImplementation implements EmployeeService {

    @Autowired
    private EmployeeRepo employeeRepo;

    public EmployeeServiceImplementation(EmployeeRepo employeeRepo) {
        super();
        this.employeeRepo = employeeRepo;
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        return employeeRepo.save(employee);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepo.findAll();
    }

    @Override
    public Employee getEmployeeById(long id) {
        Optional<Employee> employee = employeeRepo.findById(id);
        if(employee.isPresent()){
            return employee.get();
        }else{
            throw new ResourceNotFoundException("Employee", "ID", id);
        }
    }

    @Override
    public Employee updateEmployeeById(Employee employee, long id) {
        //first we need to check whether the given employee exists in DB or not
        Employee existingEmployee = employeeRepo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Employee", "ID", id));

        existingEmployee.setFirstName(employee.getFirstName());
        existingEmployee.setLastName(employee.getLastName());
        existingEmployee.setEmail(employee.getEmail());

        employeeRepo.save(existingEmployee);
        return existingEmployee;
    }

    @Override
    public void deleteEmployeeById(long id) {
        Employee existingEmployee = employeeRepo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Employee", "ID", id));
        employeeRepo.deleteById(id);
    }
}
