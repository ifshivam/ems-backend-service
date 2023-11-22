package com.emp.mgmt.controller;

import com.emp.mgmt.co_dto.co.LoginSaveCO;
import com.emp.mgmt.co_dto.dto.LoginDTO;
import com.emp.mgmt.exception.GlobalException;
import com.emp.mgmt.service.LoginLogoutService;
import com.emp.mgmt.utils.ResponseHandler;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequestMapping("/api/auth/")
public class LoginLogoutController {

    @Autowired
    LoginLogoutService loginLogoutService;

    @PostMapping("/login")
    public ResponseEntity<ResponseHandler<LoginDTO>> userLogin(@Valid @RequestBody LoginSaveCO loginSaveCO) throws GlobalException {

        ResponseHandler<LoginDTO> responseHandler = loginLogoutService.userLogin(loginSaveCO);
        return new ResponseEntity<>(responseHandler, HttpStatus.valueOf(responseHandler.getStatusCode()));
    }

    @PostMapping("/logout")
    public ResponseEntity<ResponseHandler> userLogout(@RequestHeader("Authorization") String authorizationHeader) throws GlobalException {

        ResponseHandler responseHandler = loginLogoutService.userLogout();
        return new ResponseEntity<>(responseHandler, HttpStatus.valueOf(responseHandler.getStatusCode()));
    }
}