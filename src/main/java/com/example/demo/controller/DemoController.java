package com.example.demo.controller;

import com.example.demo.model.EmployeeEntity;
import com.example.demo.service.EmployeeService;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

/*
This APIs' are as follows
All Employees whose salary is greater than X amount
All Employees whose age is less than Y
Employee whose id =Z
Employee with the highest salary
 */
@RestController
public class DemoController {

    @Autowired
    private EmployeeService service;

    @RequestMapping("/")
    public String index() {
        return "Hello World ! Greetings from Spring Boot!";
    }

    @GetMapping("/employees")
    public ResponseEntity<List<EmployeeEntity>> getAllEmployees() throws IOException, ParseException {
        List<EmployeeEntity> list = service.getAllEmployees();
        return new ResponseEntity<>(list, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/employees/salary/{salary}")
    public ResponseEntity<List<EmployeeEntity>> getAllEmployeesWhereSalaryGreater(@PathVariable Integer salary) throws IOException, ParseException {
        List<EmployeeEntity> list = service.getAllEmployeesWhereSalaryGreater(salary);
        return new ResponseEntity<>(list, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/employees/salary/max")
    public ResponseEntity<List<EmployeeEntity>> getEmployeeWithHighestSalary() throws IOException, ParseException {
        List<EmployeeEntity> employeeEntityList = service.getEmployeeWithSalary(true);
        return new ResponseEntity<>(employeeEntityList, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/employees/salary/min")
    public ResponseEntity<List<EmployeeEntity>> getEmployeeWithLowestSalary() throws IOException, ParseException {
        List<EmployeeEntity> employeeEntityList = service.getEmployeeWithSalary(false);
        return new ResponseEntity<>(employeeEntityList, new HttpHeaders(), HttpStatus.OK);
    }

//    @GetMapping("/employees/age/{age}")
//    public ResponseEntity<List<EmployeeEntity>> getAllEmployeesWhereAgeIsLess(@PathVariable Integer age) throws IOException, ParseException {
//        List<EmployeeEntity> list = service.getAllEmployeesWhereAgeIsLess(age);
//        return new ResponseEntity<>(list, new HttpHeaders(), HttpStatus.OK);
//    }
//
//    @GetMapping("/employees/{id}")
//    public ResponseEntity<EmployeeEntity> getEmployeeByProvidedId(@PathVariable Integer id) throws IOException, ParseException {
//        EmployeeEntity employeeEntity = service.getEmployeeByProvidedId(id);
//        return new ResponseEntity<>(employeeEntity, new HttpHeaders(), HttpStatus.OK);
//    }

}
