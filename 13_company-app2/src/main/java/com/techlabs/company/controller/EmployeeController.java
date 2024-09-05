package com.techlabs.company.controller;

import java.util.List;
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

import com.techlabs.company.dto.EmployeeDto;
import com.techlabs.company.dto.PageResponseDto;
import com.techlabs.company.entity.Account;
import com.techlabs.company.entity.Employee;
import com.techlabs.company.service.EmployeeService;


import jakarta.validation.Valid;

@RestController
@RequestMapping("/companyapp")
public class EmployeeController {
	@Autowired 
	EmployeeService employeeservice;
	
	@GetMapping("/employee")
	public ResponseEntity<PageResponseDto<Employee>> getAllEmployees( @RequestParam  int pageNumber, @RequestParam int pageSize){
		return ResponseEntity.ok(employeeservice.getAllEmployees(pageNumber, pageSize));	
		
	}
	
	@GetMapping("/employees")
	public ResponseEntity<List<EmployeeDto>> getAllEmployee(){
		return ResponseEntity.ok(employeeservice.getEmployees());
		
	}
	@GetMapping("/employee/{employeeId}")
	public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable int employeeId){
		return ResponseEntity.ok(employeeservice.getEmployeeById(employeeId));
	}
	
	
//	@GetMapping("/employee/{employeeId}")
//	public ResponseEntity<Optional<Employee>> getEmployeeById(@PathVariable int employeeId){
//		return ResponseEntity.ok(employeeservice.getEmployeeById(employeeId));
//	}
	
	@GetMapping("/employee/account/{employeeId}")
	public ResponseEntity<Optional<Account>> getEmployeeAccount(@PathVariable int employeeId){
		return ResponseEntity.ok(employeeservice.getEmployeeAccount(employeeId));
	}
	
//	@PostMapping("/employee")
//	public String addEmployee(@RequestBody Employee employee) {
//		employeeservice.addEmployees(employee);
//		return "Employee added successfully";
//		
//	}
	@PostMapping("/employee")
	public String addEmployee(@Valid @RequestBody EmployeeDto employeeDto) {
		employeeservice.addEmployee(employeeDto);
		return "Employee added successfully";
		
	}
	@PutMapping("/employee/account/{employeeId}")
	public String updateEmployeeAccount(@PathVariable int employeeId, @Valid @RequestBody Account account) {
		employeeservice.updateEmployeeAccount(employeeId, account);
		return "account updated successfully";
	}
	
	@PutMapping("/employee/client")
	public ResponseEntity<Employee> allocateInstructors(@RequestParam int employeeId, @RequestParam int clientId){
		return ResponseEntity.ok(employeeservice.allocateInstructor(employeeId, clientId));
	}
}
	
