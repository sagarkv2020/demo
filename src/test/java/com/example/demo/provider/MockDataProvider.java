package com.example.demo.provider;

import com.example.demo.domain.response.EmployeeResponseVO;
import com.example.demo.model.EmployeeEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class MockDataProvider {

    public static EmployeeEntity getMockEmployee() {
        EmployeeEntity employeeEntity = new EmployeeEntity("Test Employee", 12000, 26, "");
        employeeEntity.setId(1);
        return employeeEntity;
    }

    public static List<EmployeeEntity> getListOfMockEmployee() {
        EmployeeEntity employeeEntity = new EmployeeEntity("Test Employee", 12000, 26, "");
        employeeEntity.setId(1);
        EmployeeEntity employeeEntity2 = new EmployeeEntity("Test Employee2", 18000, 31, "");
        employeeEntity2.setId(2);
        EmployeeEntity employeeEntity3 = new EmployeeEntity("Test Employee3", 15000, 28, "");
        employeeEntity3.setId(3);
        return Arrays.asList(employeeEntity, employeeEntity2, employeeEntity3);
    }

    public static EmployeeResponseVO getMockEmployeeResponseVO() {
        EmployeeEntity employeeEntity = getMockEmployee();
        return new EmployeeResponseVO.EmployeeBuilder().id(employeeEntity.getId())
                .employeeAge(employeeEntity.getEmployeeAge())
                .employeeName(employeeEntity.getEmployeeName())
                .employeeSalary(employeeEntity.getEmployeeSalary())
                .profileImage(employeeEntity.getProfileImage()).build();
    }

    public static List<EmployeeResponseVO> getListOfMockEmployeeResponseVO() {
        List<EmployeeEntity> employeeEntityList = getListOfMockEmployee();
        return employeeEntityList.stream().map(employee -> new EmployeeResponseVO.EmployeeBuilder().id(employee.getId())
                .employeeAge(employee.getEmployeeAge())
                .employeeName(employee.getEmployeeName())
                .employeeSalary(employee.getEmployeeSalary())
                .profileImage(employee.getProfileImage()).build()).collect(Collectors.toList());
    }

    public static Optional<EmployeeEntity> getMockOptionalEmployee() {
       return Optional.of(getMockEmployee());
    }

}
