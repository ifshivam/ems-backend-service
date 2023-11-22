package com.emp.mgmt.co_dto.co;

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
public class FilterEmployeeSaveCO {

    Long id;

    String name;

    String email;

    Department department;

    Integer max;

    Integer offset;

    String sort;

    String order;

}
