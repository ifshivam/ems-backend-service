package com.emp.mgmt.service.impls;

import com.emp.mgmt.co_dto.co.LoginSaveCO;
import com.emp.mgmt.co_dto.dto.LoginDTO;
import com.emp.mgmt.constants.AppResponse;
import com.emp.mgmt.security.TokenProvider;
import com.emp.mgmt.service.LoginLogoutService;
import com.emp.mgmt.utils.ResponseHandler;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LoginLogoutServiceImpl implements LoginLogoutService {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    TokenProvider tokenProvider;

    @Override
    public ResponseHandler<LoginDTO> userLogin(LoginSaveCO loginSaveCO) {

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginSaveCO.getEmail(), loginSaveCO.getPassword()));
        return new ResponseHandler<LoginDTO>(new LoginDTO(tokenProvider.generateToken(authentication)),
                                             AppResponse.LOGGED_IN);
    }

    @Override
    public ResponseHandler userLogout() {
        SecurityContextHolder.getContext().setAuthentication(null);
        return new ResponseHandler<>(AppResponse.LOGGED_OUT);
    }
}
