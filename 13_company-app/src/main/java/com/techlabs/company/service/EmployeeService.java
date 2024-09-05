package com.techlabs.company.service;

import java.util.Optional;

import com.techlabs.company.dto.PageResponseDto;
import com.techlabs.company.entity.Account;
import com.techlabs.company.entity.Employee;

public interface EmployeeService {
	
	public PageResponseDto<Employee> getAllEmployees(int pageNumber, int pageSize);
	public Optional<Employee> getEmployeeById(int employeeId);
	public Optional<Account> getEmployeeAccount(int employeeId);
	public void addEmployees(Employee employee);
	public Account updateEmployeeAccount(int employeeId, Account account);
	

}
