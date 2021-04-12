package com.example.demo.controller;

import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.domain.request.EmployeeRequestVO;
import com.example.demo.domain.response.EmployeeResponseVO;
import com.example.demo.provider.MockDataProvider;
import com.example.demo.service.AbstractResponse;
import com.example.demo.service.EmployeeService;
import org.json.simple.parser.ParseException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
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

    /*@Test(expected = ResourceNotFoundException.class)
    public void testResourceNotFound() throws Exception {
        //setup
        when(mockService.getEmployeesById(anyInt())).thenThrow(ResourceNotFoundException.class);
        // Run test
        demoControllerUnderTest.getEmployeesZ(0);

    }*/


    @Test
    public void testGetAllEmployees() throws Exception {
        // Setup

        // Configure EmployeeService.getAllEmployees(...).
        when(mockService.getAllEmployees()).thenReturn(MockDataProvider.getListOfMockEmployeeResponseVO());

        // Run the test
        final ResponseEntity<List<EmployeeResponseVO>> result = demoControllerUnderTest.getAllEmployees();

        // Verify the results
        assertEquals(result.getBody().size(), 3);
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
        final List<EmployeeResponseVO> employeeEntityList = MockDataProvider.getListOfMockEmployeeResponseVO();
        when(mockService.getAllEmployeesWhereSalaryGreater(0)).thenReturn(employeeEntityList);

        // Run the test
        final ResponseEntity<List<EmployeeResponseVO>> result = demoControllerUnderTest.getAllEmployeesWhereSalaryGreater(0);

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
    public void testGetEmployeeWithHighestSalary() {
        // Setup

        // Configure EmployeeService.getEmployeeWithHighestSalary(...).
        final EmployeeResponseVO mockEmployeeResponseVO = MockDataProvider.getMockEmployeeResponseVO();
        when(mockService.getEmployeeWithSalary(true)).thenReturn(mockEmployeeResponseVO);

        // Run the test
        final ResponseEntity<EmployeeResponseVO> result = demoControllerUnderTest.getEmployeeWithHighestSalary();

        // Verify the results
        assertEquals(result.getBody(), mockEmployeeResponseVO);
    }

    @Test
    public void testGetEmployeeWithLowestSalary() {
        // Setup

        // Configure EmployeeService.getEmployeeWithHighestSalary(...).
        final EmployeeResponseVO mockEmployeeResponseVO = MockDataProvider.getMockEmployeeResponseVO();
        when(mockService.getEmployeeWithSalary(true)).thenReturn(mockEmployeeResponseVO);

        // Run the test
        final ResponseEntity<EmployeeResponseVO> result = demoControllerUnderTest.getEmployeeWithHighestSalary();

        // Verify the results
        assertEquals(result.getBody(), mockEmployeeResponseVO);
    }


    @Test
    public void testCreateEmployee() {
        // Setup
        final EmployeeRequestVO requestVO = new EmployeeRequestVO("employeeName", 0, 0, "profileImage");
        final ResponseEntity<String> expectedResult = new ResponseEntity<>("Successfully Created employeeName as Employee", HttpStatus.OK);

        // Run the test
        final ResponseEntity<String> result = demoControllerUnderTest.createEmployee(requestVO);

        // Verify the results
        assertEquals(expectedResult, result);
        verify(mockService).createEmployee(any(EmployeeRequestVO.class));
    }

    @Test
    public void testDeleteEmployee() {
        // Setup
        final ResponseEntity<String> expectedResult = new ResponseEntity<>("Successfully deleted 0 ", HttpStatus.OK);

        // Run the test
        final ResponseEntity<String> result = demoControllerUnderTest.deleteEmployee(0);

        // Verify the results
        assertEquals(expectedResult, result);
        verify(mockService).deleteEmployee(0);
    }

    @Test(expected = RuntimeException.class)
    public void testDeleteEmployee_EmployeeServiceThrowsRuntimeException() {
        // Setup
        final ResponseEntity<String> expectedResult = new ResponseEntity<>("body", HttpStatus.OK);
        doThrow(RuntimeException.class).when(mockService).deleteEmployee(0);

        // Run the test
        final ResponseEntity<String> result = demoControllerUnderTest.deleteEmployee(0);

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetAllEmployeesAsNull() throws Exception {
        // Setup

        // Configure EmployeeService.getAllEmployees(...).
        final List<EmployeeResponseVO> employeeResponseVOS = new ArrayList<>();
        when(mockService.getAllEmployees()).thenReturn(employeeResponseVOS);
        final ResponseEntity<List<EmployeeResponseVO>> expected = AbstractResponse.successResponse(new ArrayList<>());
        // Run the test
        final ResponseEntity<List<EmployeeResponseVO>> result = demoControllerUnderTest.getAllEmployees();

        // Verify the results
        assertEquals(result,  expected);
    }
}

