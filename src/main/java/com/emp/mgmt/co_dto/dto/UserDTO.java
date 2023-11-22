package com.emp.mgmt.co_dto.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDTO {

    Long id;

    String email;

    String name;

    String contact;

    String dob;

    String password;

    boolean isDeleted;

    boolean isActive;

    boolean isExpired;

    boolean isCredentialsExpired;

    boolean isLocked;

    int invalidAttemptCount;

    @Temporal(TemporalType.DATE)
    Date dateCreated;

    @Temporal(TemporalType.DATE)
    Date lastUpdated;

    String createdBy;

    String updatedBy;

}
