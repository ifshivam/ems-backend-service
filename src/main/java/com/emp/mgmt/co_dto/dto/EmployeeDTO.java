package com.emp.mgmt.co_dto.dto;

import com.emp.mgmt.co_dto.co.UserSaveCO;
import com.emp.mgmt.constants.Department;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EmployeeDTO {

    Long Id;

    String doj;

    Department department;

    Float experienceInYears;

    String accountNum;

    String ifscCode;

    UserDTO userDTO;

}
