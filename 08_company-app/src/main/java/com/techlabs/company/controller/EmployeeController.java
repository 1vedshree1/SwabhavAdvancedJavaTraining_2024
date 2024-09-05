package com.techlabs.company.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.techlabs.company.entity.Employee;
import com.techlabs.company.service.EmployeeService;




@RestController
@RequestMapping("/companyapp")
public class EmployeeController {
	@Autowired
	private EmployeeService employeeservice;
	
	@GetMapping("/employees")
	public ResponseEntity<Page<Employee>> getAllEmployees(@RequestParam(required = false)String firstName,@RequestParam int pageNumber, @RequestParam int pageSize) {
		if(firstName!=null) 
			return ResponseEntity.ok(employeeservice.getEmployeeByName(firstName, pageNumber, pageSize));
		
		return ResponseEntity.ok(employeeservice.getAllEmployees(pageSize, pageNumber));
	}
	
	
	
	
	@GetMapping("/employees/{employeeId}")
	public ResponseEntity<Optional<Employee>> getStudent(@PathVariable int employeeId){
		return ResponseEntity.ok(employeeservice.getEmployeeById(employeeId));
		
	}
	
	@PostMapping("/employees")
	public String addEmployee(@RequestBody Employee employee) {
		employeeservice.addEmployee(employee);
		return "employee added successfully.";
		
	}
	
	@DeleteMapping("/employees/{employeeId}")
	public String deleteEmployee(@PathVariable int employeeId) {
		employeeservice.deleteEmployeeById(employeeId);
		return "employee deleted successfully.";
		
	}
	

}
