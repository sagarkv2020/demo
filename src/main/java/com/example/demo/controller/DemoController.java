package com.example.demo.controller;

import java.awt.PageAttributes.MediaType;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.EmployeeEntity;
import com.example.demo.service.EmployeeService;

@RestController
public class DemoController {

	@Autowired
	EmployeeService service;

	@RequestMapping("/")
	public String index() {
		return "Hello World ! Greetings from Spring Boot!";
	}

	@GetMapping(value = "/json", produces = MediaType.APPLICATION_JSON_VALUE)
	public Hello json() {
		return new Hello("123", "Raj");
	}

	private class Hello {
		private String id;
		private String name;

		public Hello(String id, String name) {
			this.id = id;
			this.name = name;
		}

	}

	@GetMapping("/employees")
	public ResponseEntity<List<EmployeeEntity>> getAllEmployees() throws IOException, ParseException {
		List<EmployeeEntity> list = service.getAllEmployees();

		return new ResponseEntity<List<EmployeeEntity>>(list, new HttpHeaders(), HttpStatus.OK);

	}

}
