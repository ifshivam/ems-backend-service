package com.learning.emsbackendservice.service.impl;

import com.learning.emsbackendservice.dto.EmployeeDto;
import com.learning.emsbackendservice.entity.Employee;
import com.learning.emsbackendservice.exception.ResourceNotFoundException;
import com.learning.emsbackendservice.mapper.EmployeeMapper;
import com.learning.emsbackendservice.repository.EmployeeRepo;
import com.learning.emsbackendservice.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    private EmployeeRepo employeeRepo;

    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto){
        Employee employee =EmployeeMapper.mapToEmployee(employeeDto);
        Employee savedEmployee = employeeRepo.save(employee);
        return EmployeeMapper.mapToEmployeeDto(savedEmployee);
    }

    @Override
    public EmployeeDto getEmployeeById(Long employeeId){
        Employee employee=employeeRepo.findById(employeeId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("No employee exist for given Id : "+employeeId));
        return EmployeeMapper.mapToEmployeeDto(employee);
    }

    @Override
    public List<EmployeeDto> getAllEmployees(){
        List<Employee> employees=employeeRepo.findAll();
        return employees.stream().map((employee) ->
                        EmployeeMapper.mapToEmployeeDto(employee))
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeDto updateEmployee(Long employeeId,EmployeeDto updatedEmployee){
        Employee employee=employeeRepo.findById(employeeId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("No employee exist for given id : "+employeeId));
        employee.setFirstName(updatedEmployee.getFirstName());
        employee.setLastName(updatedEmployee.getLastName());
        employee.setEmail(updatedEmployee.getEmail());
        Employee updatedEmployeeObj=employeeRepo.save(employee);
        return EmployeeMapper.mapToEmployeeDto(updatedEmployeeObj);
    }
}