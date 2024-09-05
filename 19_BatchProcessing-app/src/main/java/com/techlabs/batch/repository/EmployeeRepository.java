package com.techlabs.batch.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techlabs.batch.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}
