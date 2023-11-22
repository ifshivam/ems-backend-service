package com.emp.mgmt.constants;

import org.springframework.http.HttpStatus;

public enum AppResponse {

    RESTRICTED(HttpStatus.FORBIDDEN, "Authorization Error"),
    CONFIRM_PASSWORD_MISMATCH(HttpStatus.UNPROCESSABLE_ENTITY, "Confirm Password Mismatch"),
    USER_ALREADY_EXIST(HttpStatus.CONFLICT, "User Already Exist"),
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "User Not Found"),
    INCORRECT_PASSWORD(HttpStatus.BAD_REQUEST, "Incorrect Password"),
    OK(HttpStatus.OK, "Success"),
    PROFILE_UPDATED(HttpStatus.OK, "Profile Updated"),
    PROFILE_CREATED(HttpStatus.OK, "Profile Created"),
    PROFILE_DELETED(HttpStatus.OK, "Profile Deleted"),
    PAGE_NOT_FOUND(HttpStatus.NOT_FOUND, "Page Not Found"),
    LOGGED_OUT(HttpStatus.OK, "User Logged-out Successfully"),
    LOGGED_IN(HttpStatus.OK, "User Logged-in Successfully" ),
    REGISTERED(HttpStatus.OK, "User Registered Successfully");


    private HttpStatus status;
    private String msg;

    AppResponse(HttpStatus status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getMsg() {
        return msg;
    }
}

