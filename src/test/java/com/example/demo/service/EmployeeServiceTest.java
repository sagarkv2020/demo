package com.example.demo.service;

import com.example.demo.dao.IEmployeeRepository;
import com.example.demo.model.EmployeeEntity;
import com.example.demo.provider.MockDataProvider;
import org.json.simple.parser.ParseException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeServiceTest {

    @InjectMocks
    private EmployeeService employeeServiceUnderTest;

    @Mock
    private IEmployeeRepository employeeRepository;


    @Test
    public void testGetAllEmployees() throws Exception {
        // Setup
        when(employeeRepository.getAllEmployees()).thenReturn(MockDataProvider.getListOfMockEmployee());
        // Run the test
        final List<EmployeeEntity> result = employeeServiceUnderTest.getAllEmployees();
        // Verify the results
        assertEquals(result.size(), 3);
    }

    @Test(expected = IOException.class)
    public void testGetAllEmployees_ThrowsIOException() throws Exception {
        // Setup
        when(employeeRepository.getAllEmployees()).thenThrow(IOException.class);
        // Run the test
        employeeServiceUnderTest.getAllEmployees();
    }

    @Test(expected = ParseException.class)
    public void testGetAllEmployees_ThrowsParseException() throws Exception {
        // Setup
        when(employeeRepository.getAllEmployees()).thenThrow(ParseException.class);
        // Run the test
        employeeServiceUnderTest.getAllEmployees();
    }

    @Test
    public void testGetAllEmployeesWhereSalaryGreater() throws Exception {
        // Setup
        when(employeeRepository.getAllEmployees()).thenReturn(MockDataProvider.getListOfMockEmployee());
        // Run the test
        final List<EmployeeEntity> result = employeeServiceUnderTest.getAllEmployeesWhereSalaryGreater(12000);
        // Verify the results
        assertEquals(result.size(), 2);
    }

    @Test
    public void testGetAllEmployeesWhereSalaryGreaterWithNull() throws Exception {
        // Setup
        when(employeeRepository.getAllEmployees()).thenReturn(null);
        // Run the test
        final List<EmployeeEntity> result = employeeServiceUnderTest.getAllEmployeesWhereSalaryGreater(12000);
        // Verify the results
        assertNull(result);
    }

    @Test(expected = IOException.class)
    public void testGetAllEmployeesWhereSalaryGreater_ThrowsIOException() throws Exception {
        // Setup
        when(employeeRepository.getAllEmployees()).thenThrow(IOException.class);
        // Run the test
        employeeServiceUnderTest.getAllEmployeesWhereSalaryGreater(0);
    }

    @Test(expected = ParseException.class)
    public void testGetAllEmployeesWhereSalaryGreater_ThrowsParseException() throws Exception {
        // Setup
        when(employeeRepository.getAllEmployees()).thenThrow(ParseException.class);
        // Run the test
        employeeServiceUnderTest.getAllEmployeesWhereSalaryGreater(0);
    }

    @Test
    public void testGetEmployeeWithLowestSalary() throws Exception {
        // Setup
        when(employeeRepository.getAllEmployees()).thenReturn(MockDataProvider.getListOfMockEmployee());
        // Run the test
        final List<EmployeeEntity> result = employeeServiceUnderTest.getEmployeeWithSalary(false);

        // Verify the results
        assertEquals(result.get(0).getEmployeeSalary(), 12000);
    }

    @Test
    public void testGetEmployeeWithSalaryWithNull() throws Exception {
        // Setup
        when(employeeRepository.getAllEmployees()).thenReturn(null);
        // Run the test
        final List<EmployeeEntity> result = employeeServiceUnderTest.getEmployeeWithSalary(false);

        // Verify the results
        assertNull(result);
    }

    @Test(expected = IOException.class)
    public void testGetEmployeeWithSalary_ThrowsIOException() throws Exception {
        // Setup
        when(employeeRepository.getAllEmployees()).thenThrow(IOException.class);
        // Run the test
        employeeServiceUnderTest.getEmployeeWithSalary(false);
    }

    @Test(expected = ParseException.class)
    public void testGetEmployeeWithSalary_ThrowsParseException() throws Exception {
        // Setup
        when(employeeRepository.getAllEmployees()).thenThrow(ParseException.class);
        // Run the test
        employeeServiceUnderTest.getEmployeeWithSalary(false);
    }

    @Test
    public void testGetEmployeeWithHighestSalary() throws Exception {
        // Setup
        when(employeeRepository.getAllEmployees()).thenReturn(MockDataProvider.getListOfMockEmployee());
        // Run the test
        final List<EmployeeEntity> result = employeeServiceUnderTest.getEmployeeWithSalary(true);

        // Verify the results
        assertEquals(result.get(0).getEmployeeSalary(), 18000);
    }
}
