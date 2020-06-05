package com.example.demo.controller;

import com.example.demo.model.EmployeeEntity;
import com.example.demo.service.EmployeeService;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

@RestController
public class DemoController {

	@Autowired
	EmployeeService service;

	@RequestMapping("/")
	public String index() {
		return "Hello World ! Greetings from Spring Boot!";
	}

	@GetMapping("/employees")
	public ResponseEntity<List<EmployeeEntity>> getAllEmployees() throws IOException, ParseException {
		List<EmployeeEntity> list = service.getAllEmployees();

		return new ResponseEntity<List<EmployeeEntity>>(list, new HttpHeaders(), HttpStatus.OK);

	}

}
