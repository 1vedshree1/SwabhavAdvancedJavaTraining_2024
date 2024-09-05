package com.techlabs.company.service;

import java.util.Optional;

import org.springframework.data.domain.Page;

import com.techlabs.company.entity.Employee;



public interface EmployeeService {
	public Page<Employee> getAllEmployees(int pageSize, int pageNumber);
	public Optional<Employee> getEmployeeById(int employeeId);
	public void addEmployee(Employee employee);
	public Page<Employee> getEmployeeByName(String firstName, int pageNumber,int pageSize);
	public void deleteEmployeeById(int employeeId);


}
