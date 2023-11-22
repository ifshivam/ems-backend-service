package com.emp.mgmt.service;

import com.emp.mgmt.co_dto.co.LoginSaveCO;
import com.emp.mgmt.co_dto.dto.LoginDTO;
import com.emp.mgmt.utils.ResponseHandler;

public interface LoginLogoutService {
    ResponseHandler<LoginDTO> userLogin(LoginSaveCO loginSaveCO);

    ResponseHandler userLogout();
}
