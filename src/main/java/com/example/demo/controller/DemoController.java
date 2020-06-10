package com.example.demo.controller;

import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.model.EmployeeEntity;
import com.example.demo.model.Hello;
import com.example.demo.service.EmployeeService;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
public class DemoController {

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
    public ResponseEntity<List<EmployeeEntity>> getAllEmployees() throws Exception {
        List<EmployeeEntity> list = service.getAllEmployees();
        return new ResponseEntity<List<EmployeeEntity>>(list, new HttpHeaders(), HttpStatus.OK);

    }




	@GetMapping("/employees/salary/gt/{x}")
	public ResponseEntity<List<EmployeeEntity>> getAllEmployeesSalGTx(@PathVariable int x) throws Exception {

		return new ResponseEntity<List<EmployeeEntity>>(service.getAllEmployeesSalGTx(x), new HttpHeaders(),
				HttpStatus.OK);

	}

	@GetMapping("/employees/age/lt/{y}")
	public ResponseEntity<List<EmployeeEntity>> getAllEmployeesAgeLTy(@PathVariable int y) throws Exception {

		return new ResponseEntity<List<EmployeeEntity>>(service.getAllEmployeesAgeLTy(y), new HttpHeaders(),
				HttpStatus.OK);

	}

	@GetMapping("/employees/{z}")
	public ResponseEntity<EmployeeEntity> getEmployeesZ(@PathVariable int z) throws Exception {

		EmployeeEntity emp = service.getEmployeesById(z);

		if (emp == null || emp.getId() <= 0)
			throw new ResourceNotFoundException("Employee with id: " + z + " does not Exist!!");
		else
			return new ResponseEntity<EmployeeEntity>(emp, new HttpHeaders(), HttpStatus.OK);

	}

	@GetMapping(value = "/employees/HS", produces = MediaType.APPLICATION_JSON_VALUE)
	public EmployeeEntity getEmployeesHS() throws Exception {

		return service.getEmployeeHS();

	}

	@GetMapping("/employees/salary/{salary}")
	public ResponseEntity<List<EmployeeEntity>> getAllEmployeesWhereSalaryGreater(@PathVariable Integer salary)
			throws IOException, ParseException {
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

}
