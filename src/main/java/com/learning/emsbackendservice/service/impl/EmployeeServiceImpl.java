package com.learning.emsbackendservice.service.impl;

import com.learning.emsbackendservice.dto.EmployeeDto;
import com.learning.emsbackendservice.entity.Employee;
import com.learning.emsbackendservice.mapper.EmployeeMapper;
import com.learning.emsbackendservice.repository.EmployeeRepo;
import com.learning.emsbackendservice.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
