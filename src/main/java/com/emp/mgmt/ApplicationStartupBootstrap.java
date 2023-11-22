package com.emp.mgmt;

import com.emp.mgmt.constants.AppConstants;
import com.emp.mgmt.constants.Department;
import com.emp.mgmt.constants.Role;
import com.emp.mgmt.pojos.Employee;
import com.emp.mgmt.pojos.GrantedAuthorityImpl;
import com.emp.mgmt.pojos.User;
import com.emp.mgmt.repos.EmployeeRepository;
import com.emp.mgmt.repos.UserRepository;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Date;

/**
 * Class To Bootstrap an Application.
 */
@Component
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ApplicationStartupBootstrap {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    /**
     * Setting Default Admin Account On Application Start.
     */
    @EventListener(ApplicationReadyEvent.class)
    public void runAfterStartup() {

        User user = userRepository.findByEmail("yash@alpha.com");
        if (user != null){
            AppConstants.MASTER_ADMIN.setData("yash@alpha.com");
        }else {
            user = new User();
            user.setPassword(passwordEncoder.encode("123@Billion"));
            user.setDob(new Date());

            user.setContact("88888888");
            user.setEmail("yash@alpha.com");
            user.setName("Yash Kiran");

            GrantedAuthorityImpl authority = new GrantedAuthorityImpl();
            authority.setAuthority(Role.ADMIN.name());

            user.setGrantedAuthorities(Arrays.asList(authority));

            Employee emp = new Employee();
            emp.setDoj(new Date());
            emp.setDepartment(Department.ADMIN);
            emp.setAccountNum("2005551123");
            emp.setIfscCode("SBIN001231");
            emp.setExperienceInYears(5.7f);
            emp.setUser(user);

            employeeRepository.save(emp);
            AppConstants.MASTER_ADMIN.setData(user.getEmail());
        }
    }
}