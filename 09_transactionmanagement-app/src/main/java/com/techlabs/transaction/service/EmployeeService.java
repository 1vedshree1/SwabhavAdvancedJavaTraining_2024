package com.techlabs.transaction.service;

import java.util.List;



import com.techlabs.transaction.entity.Employee;

public interface EmployeeService {
	
	public List<Employee> getAllEmployees();
	public Employee addEmployees(Employee employee, String address);
	

}
