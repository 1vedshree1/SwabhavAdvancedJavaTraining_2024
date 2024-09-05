package com.techlabs.company.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.techlabs.company.dto.PageResponseDto;
import com.techlabs.company.entity.Account;
import com.techlabs.company.entity.Employee;
import com.techlabs.company.service.EmployeeService;

@RestController
@RequestMapping("/companyapp")
public class EmployeeController {
	@Autowired 
	EmployeeService employeeservice;
	
	@GetMapping("/employee")
	public ResponseEntity<PageResponseDto<Employee>> getAllEmployees( @RequestParam  int pageNumber, @RequestParam int pageSize){
		return ResponseEntity.ok(employeeservice.getAllEmployees(pageNumber, pageSize));	
		
	}
	@GetMapping("/employee/{employeeId}")
	public ResponseEntity<Optional<Employee>> getEmployeeById(@PathVariable int employeeId){
		return ResponseEntity.ok(employeeservice.getEmployeeById(employeeId));
	}
	
	@GetMapping("/employee/account/{salaryId}")
	public ResponseEntity<Optional<Account>> getEmployeeAccount(@PathVariable int salaryId){
		return ResponseEntity.ok(employeeservice.getEmployeeAccount(salaryId));
	}
	
	@PostMapping("/employee")
	public String addEmployee(@RequestBody Employee employee) {
		employeeservice.addEmployees(employee);
		return "Employee added successfully";
		
	}
	@PutMapping("/employee/account/{employeeId}")
	public String updateEmployeeAccount(@PathVariable int employeeId, @RequestBody Account account) {
		employeeservice.updateEmployeeAccount(employeeId, account);
		return "account updated successfully";
	}
}
	
