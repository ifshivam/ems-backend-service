package com.emp.mgmt.co_dto.co;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LoginSaveCO {

    @NotNull(message = "Email Id Can't be Null")
    @Email(regexp = "^[a-zA-Z0-9._%+-]+@[alpha]+\\.[com]+$",
            message = "Invalid Email Id")
    String email;

    @NotNull(message = "Password Can't be Null")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&-+=()]).{8,15}$",
            message = "Invalid Password")
    String password;

}
