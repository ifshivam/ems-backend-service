package com.learning.emsbackendservice.repository;

import com.learning.emsbackendservice.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepo extends JpaRepository<Employee,Long>{
}
