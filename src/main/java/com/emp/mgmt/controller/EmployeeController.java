package com.emp.mgmt.controller;

import com.emp.mgmt.co_dto.co.EmployeeEditSaveCO;
import com.emp.mgmt.co_dto.co.FilterEmployeeSaveCO;
import com.emp.mgmt.co_dto.dto.AbstractEmployeeDTO;
import com.emp.mgmt.co_dto.dto.EmployeeDTO;
import com.emp.mgmt.exception.GlobalException;
import com.emp.mgmt.pojos.User;
import com.emp.mgmt.service.EmployeeService;
import com.emp.mgmt.utils.Helper;
import com.emp.mgmt.utils.ResponseHandler;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequestMapping("/api/employee")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @Autowired
    Helper helper;

    @GetMapping("/profile")
    public ResponseEntity<ResponseHandler<List<AbstractEmployeeDTO>>> getMyProfile(@RequestHeader("Authorization") String authorizationHeader) throws GlobalException {

        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        FilterEmployeeSaveCO filterEmployee = new FilterEmployeeSaveCO();
        filterEmployee.setEmail(currentUser.getEmail());
        ResponseHandler<List<AbstractEmployeeDTO>> responseHandler = employeeService.getEmployees(filterEmployee);
        return new ResponseEntity<>(responseHandler, HttpStatus.valueOf(responseHandler.getStatusCode()));
    }

    @PutMapping("/profile")
    public ResponseEntity<ResponseHandler<EmployeeDTO>> editMyProfile(@Valid @RequestBody EmployeeEditSaveCO employeeEditSaveCO,
                                                                              @RequestHeader("Authorization") String authorizationHeader) throws GlobalException {

        helper.isCurrentUser(employeeEditSaveCO.getUserSaveCo().getEmail());
        ResponseHandler<EmployeeDTO> responseHandler = employeeService.editEmp(employeeEditSaveCO);
        return new ResponseEntity<>(responseHandler, HttpStatus.valueOf(responseHandler.getStatusCode()));
    }

}
