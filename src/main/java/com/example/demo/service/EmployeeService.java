package com.example.demo.service;

import com.example.demo.dao.IEmployeeRepository;
import com.example.demo.model.EmployeeEntity;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.example.demo.dao.IEmployeeRepository;
import com.example.demo.model.EmployeeEntity;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class EmployeeService {

    @Autowired
    private IEmployeeRepository employeeRepository;

    @Autowired
    private IEmployeeRepository employeeRepository;

    /**
     * Get All Employee
     *
     * @return List of {@link EmployeeEntity}
     * @throws IOException IO Exception
     * @throws ParseException
     */
    public List<EmployeeEntity> getAllEmployees() throws IOException, ParseException {
        return employeeRepository.getAllEmployees();
    }

    /**
     * Get All Employee where Salary is Greater than Provided value
     *
     * @param salary
     * @return List {@link EmployeeEntity}
     * @throws IOException
     * @throws ParseException
     */
    public List<EmployeeEntity> getAllEmployeesWhereSalaryGreater(Integer salary) throws IOException, ParseException {
        List<EmployeeEntity> allEmployees = employeeRepository.getAllEmployees();
        System.out.println(allEmployees);
        if (!CollectionUtils.isEmpty(allEmployees)) {
            allEmployees = allEmployees.stream().filter(val -> val.getEmployeeSalary() > salary).collect(Collectors.toList());
        }
        return allEmployees;
    }
    /**
     * Get Employee with Highest Salary
     *
     * @return List {@link EmployeeEntity}
     * @throws IOException
     * @throws ParseException
     */
    public List<EmployeeEntity> getEmployeeWithSalary(boolean isMax) throws IOException, ParseException {
        List<EmployeeEntity> allEmployees = employeeRepository.getAllEmployees();
        if (!CollectionUtils.isEmpty(allEmployees)) {
            IntStream salaryStream = allEmployees.stream().mapToInt(EmployeeEntity::getEmployeeSalary);
            int maxOrMinSalary = isMax ? salaryStream.max().orElse(-1) : salaryStream.min().orElse(-1);
            allEmployees = allEmployees.stream().filter(val -> val.getEmployeeSalary() == maxOrMinSalary).collect(Collectors.toList());
        }
        return allEmployees;
	}

	public List<EmployeeEntity> getAllEmployeesSalGTx(int x) throws Exception {

		return getAllEmployees().stream().filter(e -> e.getEmployeeSalary() > x).collect(Collectors.toList());
	}

	public List<EmployeeEntity> getAllEmployeesAgeLTy(int y) throws Exception {

    /**
     * Get Employee with Highest Salary
     *
     * @return List {@link EmployeeEntity}
     * @throws IOException
     * @throws ParseException
     */
    public List<EmployeeEntity> getEmployeeWithSalary(boolean isMax) throws IOException, ParseException {
        List<EmployeeEntity> allEmployees = employeeRepository.getAllEmployees();
        if (!CollectionUtils.isEmpty(allEmployees)) {
            IntStream salaryStream = allEmployees.stream().mapToInt(EmployeeEntity::getEmployeeSalary);
            int maxOrMinSalary = isMax ? salaryStream.max().orElse(-1) : salaryStream.min().orElse(-1);
            allEmployees = allEmployees.stream().filter(val -> val.getEmployeeSalary() == maxOrMinSalary).collect(Collectors.toList());
        }
        return allEmployees;
    }
		return getAllEmployees().stream().filter(e -> e.getEmployeeAge() < y).collect(Collectors.toList());
	}

	public EmployeeEntity getEmployeesById(int z) throws Exception {

		return (EmployeeEntity) getAllEmployees().stream().filter(e -> e.getId() == z).collect(Collectors.toList());
	}

	public EmployeeEntity getEmployeeHS() throws Exception {

		return getAllEmployees().stream().sorted((e1, e2) -> e2.getEmployeeSalary() - e1.getEmployeeSalary())
				.findFirst().get();

	}
}
