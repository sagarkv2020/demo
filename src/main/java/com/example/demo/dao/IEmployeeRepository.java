package com.example.demo.dao;

import com.example.demo.model.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IEmployeeRepository extends JpaRepository<EmployeeEntity, Integer> {

    Optional<EmployeeEntity> findFirstByOrderByEmployeeSalaryAsc();

    Optional<EmployeeEntity> findFirstByOrderByEmployeeSalaryDesc();

    List<EmployeeEntity> findByEmployeeSalaryGreaterThan(int salary);
}
