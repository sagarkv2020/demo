package com.example.demo.dao;

import com.example.demo.model.entity.EmployeeEntity;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.List;

public interface IEmployeeRepository {

	List<EmployeeEntity> getAllEmployees() throws IOException, ParseException;
}

