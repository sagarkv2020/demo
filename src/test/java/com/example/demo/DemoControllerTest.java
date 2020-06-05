package com.example.demo;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.demo.controller.DemoController;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.MOCK, classes = { DemoApplication.class })
public class DemoControllerTest {

	@InjectMocks
	DemoController controller;

	private MockMvc mockMvc;

	@BeforeEach
	public void setUp() throws Exception {

		// Initialize mockAnnotations
		// MockitoAnnotations.initMocks(this);

		// Create mock mvc
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

	}

	@Test
	final void testIndex() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/"))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.content().string("Hello World ! Greetings from Spring Boot!"));
	}
	
	
	@Test
	public void testJson() throws Exception {
		mockMvc.perform(get("/json")
		.accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.id", Matchers.is("123")))
		.andExpect(jsonPath("$.name", Matchers.is("Raj")))
		.andExpect(jsonPath("$.*", Matchers.hasSize(1)));
	}
	
	
	
	

}
