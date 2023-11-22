package com.emp.mgmt.service.impls;

import com.emp.mgmt.co_dto.co.EmployeeEditSaveCO;
import com.emp.mgmt.co_dto.co.FilterEmployeeSaveCO;
import com.emp.mgmt.co_dto.dto.AbstractEmployeeDTO;
import com.emp.mgmt.co_dto.dto.EmployeeDTO;
import com.emp.mgmt.constants.AppResponse;
import com.emp.mgmt.pojos.Employee;
import com.emp.mgmt.pojos.GrantedAuthorityImpl;
import com.emp.mgmt.pojos.User;
import com.emp.mgmt.repos.EmployeeRepository;
import com.emp.mgmt.repos.UserRepository;
import com.emp.mgmt.service.EmployeeService;
import com.emp.mgmt.utils.Helper;
import com.emp.mgmt.utils.ResponseHandler;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    Helper helper;


    @Override
    public ResponseHandler<Object[]> getEmpRole(Long id) {

        Employee employee = employeeRepository.findById(id).get();
        if (employee == null)
            return new ResponseHandler<>(AppResponse.USER_NOT_FOUND);

        return new ResponseHandler<>(employee.getUser().getAuthorities().toArray(), AppResponse.OK);
    }

    @Override
    public ResponseHandler<List<AbstractEmployeeDTO>> getEmployees(FilterEmployeeSaveCO filter) {
        List<Employee> employeeList = new ArrayList<>();
        if (filter.getId() != null) {
            Long id = filter.getId();
            Optional<Employee> optionalEmployee = employeeRepository.findById(id);
            if (!optionalEmployee.isPresent()) {
                return new ResponseHandler(AppResponse.USER_NOT_FOUND);
            }
            Employee employee= optionalEmployee.get();
            employeeList.add(employee);
        } else if (filter.getEmail() != null) {
            String email = filter.getEmail();
            User user = userRepository.findByEmail(filter.getEmail());
            Employee employee = employeeRepository.findByUser(user);
            employeeList.add(employee);
        } else {
            Pageable pageable = helper.filterResultPageable(filter.getMax(),
                                        filter.getOffset(), filter.getSort(), filter.getOrder());
            if (filter.getName() != null) {
                String name = filter.getName();
                Page<Employee> employeePage = employeeRepository.findByName(name, pageable);
                if (employeePage.getContent().size() == 0) {
                    return new ResponseHandler(AppResponse.PAGE_NOT_FOUND);
                }
                for (Employee category : employeePage) {
                    employeeList.add(category);
                }
            } else if (filter.getDepartment() != null){
                Page<Employee> employeePage = employeeRepository.findALlByDepartment(filter.getDepartment(), pageable);
                if (employeePage.getContent().size() == 0) {
                    return new ResponseHandler(AppResponse.PAGE_NOT_FOUND);
                }
                for (Employee category : employeePage) {
                    employeeList.add(category);
                }
            }else {
                Page<Employee> employeePage = employeeRepository.findAll(pageable);
                if (employeePage.getContent().size() == 0) {
                    return new ResponseHandler(AppResponse.PAGE_NOT_FOUND);
                }
                for (Employee category : employeePage) {
                    employeeList.add(category);
                }
            }
        }
        List<AbstractEmployeeDTO> abstractEmployeeDTOS = helper.convertToAbstractEmployeeDTOList(employeeList);
        return new ResponseHandler(abstractEmployeeDTOS, AppResponse.OK);
    }

    @Override
    public ResponseHandler<EmployeeDTO> editEmp(EmployeeEditSaveCO employeeEditSaveCO) {

        Optional<Employee> optionalEmployee = employeeRepository.findById(employeeEditSaveCO.getId());
        if (!optionalEmployee.isPresent()) {
            return new ResponseHandler(AppResponse.USER_NOT_FOUND);
        }
        Employee employee = optionalEmployee.get();

        Employee updatedEmployee = modelMapper.map(employeeEditSaveCO, Employee.class);
        User user = modelMapper.map(employeeEditSaveCO.getUserSaveCo(), User.class);
        updatedEmployee.setUser(user);
        updatedEmployee.setId(employee.getId());
        employeeRepository.save(updatedEmployee);

        return new ResponseHandler<>(AppResponse.PROFILE_UPDATED);
    }

    @Override
    public ResponseHandler<EmployeeDTO> addEmp(EmployeeEditSaveCO employeeEditSaveCO) {

        User user = userRepository.findByEmail(employeeEditSaveCO.getUserSaveCo().getEmail());
        if (user != null)
            return new ResponseHandler<>(AppResponse.USER_ALREADY_EXIST);

        Employee employee = modelMapper.map(employeeEditSaveCO, Employee.class);
        employee.setId(null);
        user = modelMapper.map(employeeEditSaveCO.getUserSaveCo(), User.class);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        GrantedAuthorityImpl role = new GrantedAuthorityImpl();
        role.setAuthority(employeeEditSaveCO.getUserSaveCo().getRole().name());
        user.setGrantedAuthorities(Arrays.asList(role));

        employee.setUser(user);
        employeeRepository.save(employee);

        return new ResponseHandler<>(AppResponse.PROFILE_CREATED);
    }

    @Override
    public ResponseHandler<String> deleteEmp(Long id) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        if (!optionalEmployee.isPresent()) {
            return new ResponseHandler(AppResponse.USER_NOT_FOUND);
        }
        Employee employee = optionalEmployee.get();

        User user = employee.getUser();
        user.setIsDeleted(true);
        employee.setUser(user);
        employeeRepository.save(employee);

        return new ResponseHandler<>(AppResponse.PROFILE_DELETED);
    }
}
