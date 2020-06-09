package com.example.demo.controller;

import com.example.demo.domain.request.EmployeeRequestVO;
import com.example.demo.domain.response.EmployeeResponseVO;
import com.example.demo.service.AbstractResponse;
import com.example.demo.service.EmployeeService;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
public class DemoController extends AbstractResponse {

    @Autowired
    private EmployeeService service;

    @RequestMapping("/")
    public String index() {
        return "Hello World ! Greetings from Spring Boot!";
    }

    @GetMapping("/employees")
    public ResponseEntity<List<EmployeeResponseVO>> getAllEmployees() throws IOException, ParseException {
        List<EmployeeResponseVO> list = service.getAllEmployees();
        return successResponse(list);
    }

    @GetMapping("/employees/salary/{salary}")
    public ResponseEntity<List<EmployeeResponseVO>> getAllEmployeesWhereSalaryGreater(@PathVariable Integer salary) throws IOException, ParseException {
        List<EmployeeResponseVO> list = service.getAllEmployeesWhereSalaryGreater(salary);
        return successResponse(list);
    }

    @GetMapping("/employees/salary/max")
    public ResponseEntity<EmployeeResponseVO> getEmployeeWithHighestSalary() {
        EmployeeResponseVO responseVO = service.getEmployeeWithSalary(true);
        return successResponse(responseVO);
    }

    @GetMapping("/employees/salary/min")
    public ResponseEntity<EmployeeResponseVO> getEmployeeWithLowestSalary() {
        EmployeeResponseVO responseVO = service.getEmployeeWithSalary(false);
        return successResponse(responseVO);
    }

    @PostMapping(value = "/employees/create", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createEmployee(@RequestBody EmployeeRequestVO requestVO) {
        service.createEmployee(requestVO);
        return successResponse(String.format("Successfully Created %s as Employee", requestVO.getEmployeeName()));
    }

    @PutMapping(value = "/employees/update/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> updateEmployee(@RequestBody EmployeeRequestVO requestVO, @PathVariable Integer id) {
        service.updateEmployee(requestVO, id);
        return successResponse(String.format("Successfully updated %s ", requestVO.getEmployeeName()));
    }

    @DeleteMapping(value = "/employees/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deleteEmployee(@PathVariable Integer id) {
        service.deleteEmployee(id);
        return successResponse(String.format("Successfully deleted %s ", id));
    }

}
