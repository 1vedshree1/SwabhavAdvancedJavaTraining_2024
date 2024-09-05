package com.techlabs.company.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techlabs.company.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer>{
	
	

}
