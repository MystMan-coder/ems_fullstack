package com.dhananjayKr.emsbackend.repository;

import com.dhananjayKr.emsbackend.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {

}
