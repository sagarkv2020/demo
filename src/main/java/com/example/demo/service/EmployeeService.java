package com.example.demo.service;

import com.example.demo.dao.IEmployeeRepository;
import com.example.demo.domain.request.EmployeeRequestVO;
import com.example.demo.domain.response.EmployeeResponseVO;
import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.model.EmployeeEntity;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    @Autowired
    private IEmployeeRepository employeeRepository;

    /**
     * Get All Employee
     *
     * @return List of {@link EmployeeEntity}
     * @throws IOException    IO Exception
     * @throws ParseException Parse Exception
     */
    public List<EmployeeResponseVO> getAllEmployees() throws IOException, ParseException {
        List<EmployeeEntity> employeeEntityList = employeeRepository.findAll();
        List<EmployeeResponseVO> employeeResponseVOList = new ArrayList<>(0);
        if (!CollectionUtils.isEmpty(employeeEntityList)) {
            employeeResponseVOList = employeeEntityList.stream().map(this::mapEmployeeEntityToResponse)
                    .collect(Collectors.toList());
        }
        return employeeResponseVOList;
    }

    /**
     * Get All Employee where Salary is Greater than Provided value
     *
     * @param salary
     * @return List {@link EmployeeEntity}
     * @throws IOException
     * @throws ParseException
     */
    public List<EmployeeResponseVO> getAllEmployeesWhereSalaryGreater(Integer salary) throws IOException, ParseException {
        List<EmployeeEntity> allEmployees = employeeRepository.findByEmployeeSalaryGreaterThan(salary);
        List<EmployeeResponseVO> employeeResponseVOS = new ArrayList<>(0);
        System.out.println(allEmployees);
        if (!CollectionUtils.isEmpty(allEmployees)) {
            employeeResponseVOS = allEmployees.stream().map(this::mapEmployeeEntityToResponse).collect(Collectors.toList());
        }
        return employeeResponseVOS;
    }

    /**
     * Get Employee with Highest Salary
     *
     * @return List {@link EmployeeEntity}
     */
    public EmployeeResponseVO getEmployeeWithSalary(boolean isMax) {
        Optional<EmployeeEntity> employee;
        EmployeeResponseVO responseVO = null;
        if (isMax) {
            employee = employeeRepository.findFirstByOrderByEmployeeSalaryDesc();
        } else {
            employee = employeeRepository.findFirstByOrderByEmployeeSalaryAsc();
        }
        if (employee.isPresent()) {
            responseVO = mapEmployeeEntityToResponse(employee.get());
        }
        return responseVO;
    }

    /**
     * Create new Employee
     *
     * @param requestVO {@link EmployeeRequestVO}
     */
    public void createEmployee(EmployeeRequestVO requestVO) {
        EmployeeEntity employeeEntity = new EmployeeEntity(requestVO.getEmployeeName(), requestVO.getEmployeeSalary(), requestVO.getEmployeeAge(), requestVO.getProfileImage());
        employeeEntity.setCreatedBy(employeeEntity.getEmployeeName());
        employeeEntity.setActive(true);
        employeeRepository.save(employeeEntity);
    }

    /**
     * Update Employee by Id
     *
     * @param requestVO {@link EmployeeRequestVO}
     * @param id        ID of the Employee
     */
    public void updateEmployee(EmployeeRequestVO requestVO, Integer id) throws ResourceNotFoundException {
        Optional<EmployeeEntity> entityOptional = employeeRepository.findById(id);
        if (entityOptional.isPresent()) {
            EmployeeEntity employeeEntity = entityOptional.get();
            if (requestVO.getEmployeeName() != null) {
                employeeEntity.setEmployeeName(requestVO.getEmployeeName());
            }
            if (requestVO.getEmployeeSalary() > 0) {
                employeeEntity.setEmployeeSalary(requestVO.getEmployeeSalary());
            }
            if (requestVO.getEmployeeAge() > 0) {
                employeeEntity.setEmployeeAge(requestVO.getEmployeeAge());
            }
            if (requestVO.getProfileImage() != null) {
                employeeEntity.setProfileImage(requestVO.getProfileImage());
            }
            employeeEntity.setModifiedBy(employeeEntity.getEmployeeName());
            employeeRepository.save(employeeEntity);
        } else {
            throw new ResourceNotFoundException("No Employee Found");
        }
    }

    /**
     * Delete Employee By ID
     *
     * @param id Employee ID
     */
    public void deleteEmployee(Integer id) throws ResourceNotFoundException {
        Optional<EmployeeEntity> entityOptional = employeeRepository.findById(id);
        if (entityOptional.isPresent()) {
            employeeRepository.delete(entityOptional.get());
        } else {
            throw new ResourceNotFoundException("No Employee Found");
        }
    }

    public List<EmployeeResponseVO> getAllEmployeesSalGTx(int x) throws Exception {
        List<EmployeeEntity> employeeEntityList = employeeRepository.findAll();
        List<EmployeeResponseVO> employeeResponseVOList = new ArrayList<>(0);
        if (!CollectionUtils.isEmpty(employeeEntityList)) {
            employeeResponseVOList = employeeEntityList.stream().filter(e -> e.getEmployeeSalary() > x)
                    .map(this::mapEmployeeEntityToResponse)
                    .collect(Collectors.toList());
        }
        return employeeResponseVOList;
    }

    /**
     * Map Employee Entity to Employee Response VO
     *
     * @param employee {@link EmployeeEntity}
     * @return response {@link EmployeeRequestVO}
     */
    private EmployeeResponseVO mapEmployeeEntityToResponse(EmployeeEntity employee) {
        return new EmployeeResponseVO.EmployeeBuilder().id(employee.getId())
                .employeeAge(employee.getEmployeeAge())
                .employeeName(employee.getEmployeeName())
                .employeeSalary(employee.getEmployeeSalary())
                .profileImage(employee.getProfileImage()).build();
    }

    public List<EmployeeResponseVO> getAllEmployeesAgeLTy(int y) throws Exception {
        List<EmployeeEntity> employeeEntityList = employeeRepository.findAll();
        List<EmployeeResponseVO> employeeResponseVOList = new ArrayList<>(0);
        if (!CollectionUtils.isEmpty(employeeEntityList)) {
            employeeResponseVOList = employeeEntityList.stream().filter(e -> e.getEmployeeSalary() < y)
                    .map(this::mapEmployeeEntityToResponse)
                    .collect(Collectors.toList());
        }
        return employeeResponseVOList;
    }

    public EmployeeResponseVO getEmployeesById(int id) throws ResourceNotFoundException {

        Optional<EmployeeEntity> entityOptional = employeeRepository.findById(id);
        if (entityOptional.isPresent()) {
            return mapEmployeeEntityToResponse(entityOptional.get());
        } else {
            throw new ResourceNotFoundException("No Employee Found");
        }
    }

}
