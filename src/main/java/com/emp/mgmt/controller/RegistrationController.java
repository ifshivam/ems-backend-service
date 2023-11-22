package com.emp.mgmt.controller;

import com.emp.mgmt.co_dto.co.RegisterEmpSaveCO;
import com.emp.mgmt.co_dto.dto.LoginDTO;
import com.emp.mgmt.exception.GlobalException;
import com.emp.mgmt.service.RegistrationService;
import com.emp.mgmt.utils.ResponseHandler;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequestMapping("/api")
public class RegistrationController {

    @Autowired
    RegistrationService registrationService;

    @PostMapping("/register")
    public ResponseEntity<ResponseHandler<LoginDTO>> addEmp(@Valid @RequestBody RegisterEmpSaveCO registerEmpSaveCO) throws GlobalException {

        ResponseHandler<LoginDTO> responseHandler = registrationService.registerEmp(registerEmpSaveCO);
        return new ResponseEntity<>(responseHandler, HttpStatus.valueOf(responseHandler.getStatusCode()));
    }

}
