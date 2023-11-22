package com.emp.mgmt.utils;

import com.emp.mgmt.constants.AppResponse;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;

/**
 * Class to Send Custom Response.
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseHandler<T> {

    T data;
    String msg;
    int statusCode;

    public ResponseHandler(T data, AppResponse appResponse) {

        this.data = data;
        this.msg = appResponse.getMsg();
        this.statusCode = appResponse.getStatus().value();
    }

    public ResponseHandler(AppResponse appResponse) {

        this.msg = appResponse.getMsg();
        this.statusCode = appResponse.getStatus().value();
    }

    @Override
    public String toString() {
        return "{" +
                "msg='" + msg + '\'' +
                ", statusCode=" + statusCode +
                '}';
    }

}
