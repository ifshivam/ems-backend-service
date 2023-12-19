package com.emp.mgmt.repos;

import com.emp.mgmt.constants.Department;
import com.emp.mgmt.pojos.Employee;
import com.emp.mgmt.pojos.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Employee findByUser(User user);

    @Query(nativeQuery = true, value = "select * from employee where user_id in (select id from ems_user where name = :name)")
    Page<Employee> findByName(@Param("name") String name, Pageable pageable);

    Page<Employee> findALlByDepartment(Department department, Pageable pageable);
}
