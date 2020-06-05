package com.example.demo.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

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
}
