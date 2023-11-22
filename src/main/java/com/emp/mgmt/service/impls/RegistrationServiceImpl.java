package com.emp.mgmt.service.impls;

import com.emp.mgmt.co_dto.co.RegisterEmpSaveCO;
import com.emp.mgmt.co_dto.dto.EmployeeDTO;
import com.emp.mgmt.co_dto.dto.LoginDTO;
import com.emp.mgmt.constants.AppResponse;
import com.emp.mgmt.constants.Role;
import com.emp.mgmt.pojos.Employee;
import com.emp.mgmt.pojos.GrantedAuthorityImpl;
import com.emp.mgmt.pojos.User;
import com.emp.mgmt.repos.EmployeeRepository;
import com.emp.mgmt.repos.UserRepository;
import com.emp.mgmt.security.TokenProvider;
import com.emp.mgmt.service.RegistrationService;
import com.emp.mgmt.utils.ResponseHandler;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RegistrationServiceImpl implements RegistrationService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    TokenProvider tokenProvider;

    @Override
    public ResponseHandler<LoginDTO> registerEmp(RegisterEmpSaveCO registerEmpSaveCO) {

        if (!registerEmpSaveCO.getRegisterUserSaveCO().getPassword()
                .equals(registerEmpSaveCO.getRegisterUserSaveCO().getConfirmPassword()))
            return new ResponseHandler<>(AppResponse.CONFIRM_PASSWORD_MISMATCH);

        User user = userRepository.findByEmail(registerEmpSaveCO.getRegisterUserSaveCO().getEmail());
        if (user != null)
            return new ResponseHandler<>(AppResponse.USER_ALREADY_EXIST);

        Employee employee = modelMapper.map(registerEmpSaveCO, Employee.class);
        user = modelMapper.map(registerEmpSaveCO.getRegisterUserSaveCO(), User.class);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        GrantedAuthorityImpl role = new GrantedAuthorityImpl();
        role.setAuthority(Role.EMPLOYEE.name());
        user.setGrantedAuthorities(Arrays.asList(role));

        employee.setUser(user);
        employeeRepository.save(employee);

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                registerEmpSaveCO.getRegisterUserSaveCO().getEmail(), registerEmpSaveCO.getRegisterUserSaveCO().getPassword()));
        return new ResponseHandler<LoginDTO>(new LoginDTO(tokenProvider.generateToken(authentication)),
                AppResponse.REGISTERED);
    }

}
