package com.example.demo.controller;

import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.model.entity.EmployeeEntity;
import com.example.demo.provider.MockDataProvider;
import com.example.demo.service.EmployeeService;
import org.json.simple.parser.ParseException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DemoControllerTest {

    @Mock
    private EmployeeService mockService;

    @InjectMocks
    private DemoController demoControllerUnderTest;

    @Before
    public void setUp() {

    }

    @Test
    public void testIndex() {
        // Run the test
        final String result = demoControllerUnderTest.index();
        // Verify the results
        assertEquals("Hello World ! Greetings from Spring Boot!", result);
    }

    @Test(expected = ResourceNotFoundException.class)
    public void testResourceNotFound() throws Exception {
        //setup
        when(mockService.getEmployeesById(anyInt())).thenThrow(ResourceNotFoundException.class);
        // Run test
        demoControllerUnderTest.getEmployeesZ(0);

    }


    @Test
    public void testGetAllEmployees() throws Exception {
        // Setup

        // Configure EmployeeService.getAllEmployees(...).
        final EmployeeEntity employeeEntity = MockDataProvider.getMockEmployee();
        final List<EmployeeEntity> employeeEntityList = Collections.singletonList(employeeEntity);
        when(mockService.getAllEmployees()).thenReturn(employeeEntityList);

        // Run the test
        final ResponseEntity<List<EmployeeEntity>> result = demoControllerUnderTest.getAllEmployees();

        // Verify the results
        assertEquals(result.getBody().size(), 1);
    }

    @Test(expected = IOException.class)
    public void testGetAllEmployees_EmployeeServiceThrowsIOException() throws Exception {
        // Setup
        when(mockService.getAllEmployees()).thenThrow(IOException.class);

        // Run the test
        demoControllerUnderTest.getAllEmployees();
    }

    @Test(expected = ParseException.class)
    public void testGetAllEmployees_EmployeeServiceThrowsParseException() throws Exception {
        // Setup
        when(mockService.getAllEmployees()).thenThrow(ParseException.class);

        // Run the test
        demoControllerUnderTest.getAllEmployees();
    }

    @Test
    public void testGetAllEmployeesWhereSalaryGreater() throws Exception {
        // Setup

        // Configure EmployeeService.getAllEmployeesWhereSalaryGreater(...).
        final List<EmployeeEntity> employeeEntityList = MockDataProvider.getListOfMockEmployee();
        when(mockService.getAllEmployeesWhereSalaryGreater(0)).thenReturn(employeeEntityList);

        // Run the test
        final ResponseEntity<List<EmployeeEntity>> result = demoControllerUnderTest.getAllEmployeesWhereSalaryGreater(0);

        // Verify the results
        assertEquals(result.getBody().size(), 3);
    }

    @Test(expected = IOException.class)
    public void testGetAllEmployeesWhereSalaryGreater_EmployeeServiceThrowsIOException() throws Exception {
        // Setup
        when(mockService.getAllEmployeesWhereSalaryGreater(0)).thenThrow(IOException.class);

        // Run the test
        demoControllerUnderTest.getAllEmployeesWhereSalaryGreater(0);
    }

    @Test(expected = ParseException.class)
    public void testGetAllEmployeesWhereSalaryGreater_EmployeeServiceThrowsParseException() throws Exception {
        // Setup
        when(mockService.getAllEmployeesWhereSalaryGreater(0)).thenThrow(ParseException.class);

        // Run the test
        demoControllerUnderTest.getAllEmployeesWhereSalaryGreater(0);
    }

    @Test
    public void testGetEmployeeWithHighestSalary() throws Exception {
        // Setup

        // Configure EmployeeService.getEmployeeWithHighestSalary(...).
        final List<EmployeeEntity> employeeEntityList = MockDataProvider.getListOfMockEmployee();;
        when(mockService.getEmployeeWithSalary(true)).thenReturn(employeeEntityList);

        // Run the test
        final ResponseEntity<List<EmployeeEntity>> result = demoControllerUnderTest.getEmployeeWithHighestSalary();

        // Verify the results
        assertEquals(result.getBody().size(), 3);
    }

    @Test(expected = IOException.class)
    public void testGetEmployeeWithHighestSalary_EmployeeServiceThrowsIOException() throws Exception {
        // Setup
        when(mockService.getEmployeeWithSalary(true)).thenThrow(IOException.class);

        // Run the test
        demoControllerUnderTest.getEmployeeWithHighestSalary();
    }

    @Test(expected = ParseException.class)
    public void testGetEmployeeWithHighestSalary_EmployeeServiceThrowsParseException() throws Exception {
        // Setup
        when(mockService.getEmployeeWithSalary(true)).thenThrow(ParseException.class);

        // Run the test
        demoControllerUnderTest.getEmployeeWithHighestSalary();
    }

    @Test
    public void testGetEmployeeWithLowestSalary() throws Exception {
        // Setup

        // Configure EmployeeService.getEmployeeWithHighestSalary(...).
        final EmployeeEntity employeeEntity = MockDataProvider.getMockEmployee();
        final List<EmployeeEntity> employeeEntityList = Arrays.asList(employeeEntity);
        when(mockService.getEmployeeWithSalary(false)).thenReturn(employeeEntityList);

        // Run the test
        final ResponseEntity<List<EmployeeEntity>> result = demoControllerUnderTest.getEmployeeWithLowestSalary();

        // Verify the results
        assertEquals(result.getBody().size(), 1);
    }

    @Test(expected = IOException.class)
    public void testGetEmployeeWithLowestSalary_EmployeeServiceThrowsIOException() throws Exception {
        // Setup
        when(mockService.getEmployeeWithSalary(false)).thenThrow(IOException.class);

        // Run the test
        demoControllerUnderTest.getEmployeeWithLowestSalary();
    }

    @Test(expected = ParseException.class)
    public void testGetEmployeeWithLowestSalary_EmployeeServiceThrowsParseException() throws Exception {
        // Setup
        when(mockService.getEmployeeWithSalary(false)).thenThrow(ParseException.class);

        // Run the test
        demoControllerUnderTest.getEmployeeWithLowestSalary();
    }

}

