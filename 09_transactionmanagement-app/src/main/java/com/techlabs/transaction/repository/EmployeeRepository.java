package com.techlabs.transaction.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.techlabs.transaction.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee,Integer>{

	
	
	

}
