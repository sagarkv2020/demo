package com.example.demo.provider;

import com.example.demo.model.EmployeeEntity;

import java.util.Arrays;
import java.util.List;

public class MockDataProvider {

    public static EmployeeEntity getMockEmployee() {
        EmployeeEntity employeeEntity = new EmployeeEntity();
        employeeEntity.setId(1);
        employeeEntity.setEmployeeName("Test Employee");
        employeeEntity.setEmployeeSalary(12000);
        employeeEntity.setEmployeeAge(26);
        employeeEntity.setProfileImage("");
        return employeeEntity;
    }

    public static List<EmployeeEntity> getListOfMockEmployee() {
        EmployeeEntity employeeEntity = new EmployeeEntity();
        employeeEntity.setId(1);
        employeeEntity.setEmployeeName("Test Employee");
        employeeEntity.setEmployeeSalary(12000);
        employeeEntity.setEmployeeAge(26);
        employeeEntity.setProfileImage("");
        EmployeeEntity employeeEntity2 = new EmployeeEntity();
        employeeEntity2.setId(2);
        employeeEntity2.setEmployeeName("Test Employee");
        employeeEntity2.setEmployeeSalary(18000);
        employeeEntity2.setEmployeeAge(31);
        employeeEntity2.setProfileImage("");
        EmployeeEntity employeeEntity3 = new EmployeeEntity();
        employeeEntity3.setId(3);
        employeeEntity3.setEmployeeName("Test Employee");
        employeeEntity3.setEmployeeSalary(15000);
        employeeEntity3.setEmployeeAge(28);
        employeeEntity3.setProfileImage("");
        return Arrays.asList(employeeEntity, employeeEntity2, employeeEntity3);
    }

}