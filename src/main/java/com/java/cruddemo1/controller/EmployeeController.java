package com.java.cruddemo1.controller;

import com.java.cruddemo1.model.Employee;
import com.java.cruddemo1.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    //Constructor-based dependency injection because employeeService is mandatory
    public EmployeeController(EmployeeService employeeService) {
        super();
        this.employeeService = employeeService;
    }

    //CREATE Employee REST API
    @PostMapping
    public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee){
        return new ResponseEntity<Employee> (employeeService.saveEmployee(employee), HttpStatus.CREATED);
    }

    //READ Employee REST API
    @GetMapping
    public List<Employee> getAllEmployees(){
        return employeeService.getAllEmployees();
    }

    //FIND Employee by ID REST API
    @GetMapping("{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") long employeeId){
        return new ResponseEntity<Employee>(employeeService.getEmployeeById(employeeId), HttpStatus.OK);
    }

    //UPDATE Employee by ID REST API
    @PutMapping("{id}")
    public ResponseEntity<Employee> updateEmployeeById(@PathVariable("id") long employeeId, @RequestBody Employee employee){
        return new ResponseEntity<Employee>(employeeService.updateEmployeeById(employee, employeeId), HttpStatus.OK);
    }

    //DELETE Employee by ID REST API
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteEmployeeById(@PathVariable("id") long id){
        employeeService.deleteEmployeeById(id);
        return new ResponseEntity<String>("Employee Successfully Deleted", HttpStatus.OK);
    }




}
