package com.emp.mgmt.service;

import com.emp.mgmt.co_dto.co.RegisterEmpSaveCO;
import com.emp.mgmt.co_dto.dto.EmployeeDTO;
import com.emp.mgmt.co_dto.dto.LoginDTO;
import com.emp.mgmt.utils.ResponseHandler;

public interface RegistrationService {
    ResponseHandler<LoginDTO> registerEmp(RegisterEmpSaveCO registerEmpSaveCO);

}
