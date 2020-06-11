package com.example.demo.controller;

import com.example.demo.domain.request.EmployeeRequestVO;
import com.example.demo.domain.response.EmployeeResponseVO;
import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.model.Hello;
import com.example.demo.service.AbstractResponse;
import com.example.demo.service.EmployeeService;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
public class DemoController extends AbstractResponse {

    @Autowired
    private EmployeeService service;

    @RequestMapping("/")
    public String index() {
        return "Hello World ! Greetings from Spring Boot!";
    }

    @GetMapping(value = "/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public Hello json() {
        return new Hello("123", "Raj");
    }

    @GetMapping("/employees")
    public ResponseEntity<List<EmployeeResponseVO>> getAllEmployees() throws IOException, ParseException {
        List<EmployeeResponseVO> list = service.getAllEmployees();
        return successResponse(list);
    }


    @GetMapping("/employees/salary/gt/{x}")
    public ResponseEntity<List<EmployeeResponseVO>> getAllEmployeesSalGTx(@PathVariable int x) throws Exception {

        return new ResponseEntity<>(service.getAllEmployeesSalGTx(x), new HttpHeaders(),
                HttpStatus.OK);

    }

    @GetMapping("/employees/age/lt/{y}")
    public ResponseEntity<List<EmployeeResponseVO>> getAllEmployeesAgeLTy(@PathVariable int y) throws Exception {

        return new ResponseEntity<>(service.getAllEmployeesAgeLTy(y), new HttpHeaders(),
                HttpStatus.OK);

    }

    @GetMapping("/employees/{z}")
    public ResponseEntity<EmployeeResponseVO> getEmployeesZ(@PathVariable int z) throws Exception {

        EmployeeResponseVO emp = service.getEmployeesById(z);

        if (emp == null || emp.getId() <= 0)
            throw new ResourceNotFoundException("Employee with id: " + z + " does not Exist!!");
        else
            return new ResponseEntity<>(emp, new HttpHeaders(), HttpStatus.OK);

    }

    @GetMapping(value = "/employees/HS", produces = MediaType.APPLICATION_JSON_VALUE)
    public EmployeeResponseVO getEmployeesHS() throws Exception {
        return service.getEmployeeWithSalary(true);
    }

    @GetMapping("/employees/salary/{salary}")
    public ResponseEntity<List<EmployeeResponseVO>> getAllEmployeesWhereSalaryGreater(@PathVariable Integer salary)
            throws IOException, ParseException {
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
