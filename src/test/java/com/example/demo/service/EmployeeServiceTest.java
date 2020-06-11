package com.example.demo.service;

import com.example.demo.dao.IEmployeeRepository;
import com.example.demo.domain.request.EmployeeRequestVO;
import com.example.demo.domain.response.EmployeeResponseVO;
import com.example.demo.model.EmployeeEntity;
import com.example.demo.provider.MockDataProvider;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
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
        when(employeeRepository.findAll()).thenReturn(MockDataProvider.getListOfMockEmployee());
        // Run the test
        final List<EmployeeResponseVO> result = employeeServiceUnderTest.getAllEmployees();
        // Verify the results
        assertEquals(result.size(), 3);
    }


    @Test
    public void testGetAllEmployeesWhereSalaryGreater() throws Exception {
        // Setup
        when(employeeRepository.findByEmployeeSalaryGreaterThan(anyInt())).thenReturn(MockDataProvider.getListOfMockEmployee());
        // Run the test
        final List<EmployeeResponseVO> result = employeeServiceUnderTest.getAllEmployeesWhereSalaryGreater(12000);
        // Verify the results
        assertEquals(result.size(), 3);
    }


    @Test
    public void testGetAllEmployeesWhereSalaryGreaterWithNull() throws Exception {
        // Setup
        when(employeeRepository.findByEmployeeSalaryGreaterThan(anyInt())).thenReturn(new ArrayList<>());
        // Run the test
        final List<EmployeeResponseVO> result = employeeServiceUnderTest.getAllEmployeesWhereSalaryGreater(12000);
        // Verify the results
        assertEquals(result, new ArrayList<>());
    }

    @Test
    public void testGetEmployeeWithLowestSalary() {
        // Setup
        when(employeeRepository.findFirstByOrderByEmployeeSalaryAsc()).thenReturn(MockDataProvider.getMockOptionalEmployee());
        // Run the test
        final EmployeeResponseVO result = employeeServiceUnderTest.getEmployeeWithSalary(false);

        // Verify the results
        assertEquals(result.getEmployeeSalary(), 12000);
    }

    @Test
    public void testGetEmployeeWithSalaryWithNull() {
        // Setup
        // Run the test
        final EmployeeResponseVO result = employeeServiceUnderTest.getEmployeeWithSalary(false);

        // Verify the results
        assertNull(result);
    }

    @Test
    public void testGetEmployeeWithHighestSalary() {
        // Setup
        when(employeeRepository.findFirstByOrderByEmployeeSalaryDesc()).thenReturn(MockDataProvider.getMockOptionalEmployee());
        // Run the test
        final EmployeeResponseVO result = employeeServiceUnderTest.getEmployeeWithSalary(true);

        // Verify the results
        assertEquals(result.getEmployeeSalary(), 12000);
    }

    @Test
    public void testCreateEmployee() {
        // Setup
        final EmployeeRequestVO requestVO = new EmployeeRequestVO("employeeName", 0, 0, "profileImage");

        // Configure IEmployeeRepository.save(...).
        final EmployeeEntity employeeEntity = new EmployeeEntity("employeeName", 0, 0, "profileImage");
        when(employeeRepository.save(any(EmployeeEntity.class))).thenReturn(employeeEntity);

        // Run the test
        employeeServiceUnderTest.createEmployee(requestVO);

        // Verify the results
    }

    @Test
    public void testGetAllEmployeesNull() throws Exception {
        // Configure EmployeeService.getAllEmployees(...).
        final List<EmployeeEntity> employeeEntityList = new ArrayList<>();
        when(employeeRepository.findAll()).thenReturn(employeeEntityList);

        // Run the test
        final List<EmployeeResponseVO> result = employeeServiceUnderTest.getAllEmployees();

        // Verify the results
        assertEquals(result, new ArrayList<>());
    }
}
