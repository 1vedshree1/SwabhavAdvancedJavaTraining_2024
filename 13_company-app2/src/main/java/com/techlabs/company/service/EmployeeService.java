package com.techlabs.company.service;

import java.util.List;
import java.util.Optional;

import com.techlabs.company.dto.EmployeeDto;
import com.techlabs.company.dto.PageResponseDto;
import com.techlabs.company.entity.Account;
import com.techlabs.company.entity.Employee;

public interface EmployeeService {
	
	public PageResponseDto<Employee> getAllEmployees(int pageNumber, int pageSize);
//	public Optional<Employee> getEmployeeById(int employeeId);
	public Optional<Account> getEmployeeAccount(int employeeId);
	public void addEmployees(Employee employee);
	public Account updateEmployeeAccount(int employeeId, Account account);
	
	EmployeeDto addEmployee(EmployeeDto employee);
	List<EmployeeDto> getEmployees();
	EmployeeDto getEmployeeById(int EmployeeId);
	Employee allocateInstructor(int employeeId, int clientId);
	
	
	

}
