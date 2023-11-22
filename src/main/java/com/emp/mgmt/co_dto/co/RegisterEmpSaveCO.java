package com.emp.mgmt.co_dto.co;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RegisterEmpSaveCO {

    Float experienceInYears;

    String accountNum;

    String ifscCode;

    RegisterUserSaveCO registerUserSaveCO;

}
