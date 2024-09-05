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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.GetExchange;

import com.techlabs.company.dto.PageResponseDto;
import com.techlabs.company.entity.Salary;
import com.techlabs.company.entity.Transaction;
import com.techlabs.company.service.SalaryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/companyapp")
public class SalaryController {
	@Autowired
	private SalaryService salaryservice;
	
	@GetMapping("/salary")
	public ResponseEntity<PageResponseDto<Salary>> getAllSalary(int pageNumber, int pageSize){
		return ResponseEntity.ok(salaryservice.getAllSalaries(pageNumber, pageSize));		
	}
	
	@GetMapping("/salary/{salaryId}")
	public ResponseEntity<Optional<Salary>> getSalaryById(@PathVariable int salaryId){
		return ResponseEntity.ok(salaryservice.getSalaryById(salaryId));
		
	}
	
	@GetMapping("/salary/transaction/{salaryId}")
	public ResponseEntity<Optional<Transaction>> getSalaryTransaction(@PathVariable int salaryId){
		return ResponseEntity.ok(salaryservice.getSalaryTransaction(salaryId));
		
	}
	
	@PostMapping("/salary")
	public String addSalary(@Valid @RequestBody Salary salary) {
		salaryservice.addSalary(salary);
		return "Salary added successfully";
	}
	
	@PutMapping("salary/transaction/{salaryId}")
	public String updateSalaryTransaction(@PathVariable int salaryId, @RequestBody Transaction transaction) {
		return "transaction updated successfully";
		
	}

}
