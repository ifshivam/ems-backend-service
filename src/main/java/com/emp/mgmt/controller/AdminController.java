package com.emp.mgmt.controller;

import com.emp.mgmt.co_dto.co.EmployeeEditSaveCO;
import com.emp.mgmt.co_dto.co.FilterEmployeeSaveCO;
import com.emp.mgmt.co_dto.dto.AbstractEmployeeDTO;
import com.emp.mgmt.co_dto.dto.EmployeeDTO;
import com.emp.mgmt.exception.GlobalException;
import com.emp.mgmt.service.EmployeeService;
import com.emp.mgmt.utils.ResponseHandler;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    EmployeeService employeeService;

    @GetMapping("/emp/{id}/role")
    public ResponseEntity<ResponseHandler<Object[]>> getEmpRole(@PathVariable("id") Long id,
                                                                @RequestHeader("Authorization") String authorizationHeader) throws GlobalException {

        ResponseHandler<Object[]> responseHandler = employeeService.getEmpRole(id);
        return new ResponseEntity<>(responseHandler, HttpStatus.valueOf(responseHandler.getStatusCode()));
    }

    @GetMapping("/employees")
    public ResponseEntity<ResponseHandler<List<AbstractEmployeeDTO>>> getFilterEmployees(@RequestBody(required = false) FilterEmployeeSaveCO filterEmployee,
                                                                                    @RequestHeader("Authorization") String authorizationHeader) throws GlobalException {

        ResponseHandler<List<AbstractEmployeeDTO>> responseHandler = employeeService.getEmployees(filterEmployee);
        return new ResponseEntity<>(responseHandler, HttpStatus.valueOf(responseHandler.getStatusCode()));
    }

    @PutMapping("/emp/edit")
    public ResponseEntity<ResponseHandler<EmployeeDTO>> editEmp(@Valid @RequestBody EmployeeEditSaveCO employeeEditSaveCO,
                                                                @RequestHeader("Authorization") String authorizationHeader) throws GlobalException {

        ResponseHandler<EmployeeDTO> responseHandler = employeeService.editEmp(employeeEditSaveCO);
        return new ResponseEntity<>(responseHandler, HttpStatus.valueOf(responseHandler.getStatusCode()));
    }

    @PostMapping("/emp/add")
    public ResponseEntity<ResponseHandler<EmployeeDTO>> addEmp(@Valid @RequestBody EmployeeEditSaveCO employeeEditSaveCO,
                                                               @RequestHeader("Authorization") String authorizationHeader) throws GlobalException {

        ResponseHandler<EmployeeDTO> responseHandler = employeeService.addEmp(employeeEditSaveCO);
        return new ResponseEntity<>(responseHandler, HttpStatus.valueOf(responseHandler.getStatusCode()));
    }

    @DeleteMapping("/emp/{id}/delete")
    public ResponseEntity<ResponseHandler<String>> deleteEmp(@PathVariable("id") Long id,
                                                             @RequestHeader("Authorization") String authorizationHeader) throws GlobalException {

        ResponseHandler<String> responseHandler = employeeService.deleteEmp(id);
        return new ResponseEntity<>(responseHandler, HttpStatus.valueOf(responseHandler.getStatusCode()));
    }

}
