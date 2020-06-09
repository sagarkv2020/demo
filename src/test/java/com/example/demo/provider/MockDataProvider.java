package com.example.demo.provider;

import com.example.demo.model.EmployeeEntity;

import java.util.Arrays;
import java.util.List;

public class MockDataProvider {

    public static EmployeeEntity getMockEmployee() {
        return new EmployeeEntity(1, "Test Employee", 12000, 26, "");
    }

    public static List<EmployeeEntity> getListOfMockEmployee() {
        EmployeeEntity employeeEntity = new EmployeeEntity(1, "Test Employee", 12000, 26, "");
        EmployeeEntity employeeEntity2 = new EmployeeEntity(2, "Test Employee2", 18000, 31, "");
        EmployeeEntity employeeEntity3 = new EmployeeEntity(3, "Test Employee3", 15000, 28, "");
        return Arrays.asList(employeeEntity, employeeEntity2, employeeEntity3);
    }

}
