package com.emp.mgmt.service;

import com.emp.mgmt.co_dto.co.EmployeeEditSaveCO;
import com.emp.mgmt.co_dto.co.FilterEmployeeSaveCO;
import com.emp.mgmt.co_dto.dto.AbstractEmployeeDTO;
import com.emp.mgmt.co_dto.dto.EmployeeDTO;
import com.emp.mgmt.utils.ResponseHandler;

import java.util.List;

public interface EmployeeService {
    ResponseHandler<Object[]> getEmpRole(Long id);

    ResponseHandler<List<AbstractEmployeeDTO>> getEmployees(FilterEmployeeSaveCO filterEmployee);

    ResponseHandler<EmployeeDTO> editEmp(EmployeeEditSaveCO employeeEditSaveCO);

    ResponseHandler<EmployeeDTO> addEmp(EmployeeEditSaveCO employeeEditSaveCO);

    ResponseHandler<String> deleteEmp(Long id);
}
