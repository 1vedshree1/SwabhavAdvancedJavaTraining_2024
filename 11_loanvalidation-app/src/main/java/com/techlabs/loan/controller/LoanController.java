package com.techlabs.loan.controller;

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

import com.techlabs.loan.dto.PageResponseDto;
import com.techlabs.loan.entity.Customer;
import com.techlabs.loan.entity.Loan;
import com.techlabs.loan.service.LoanService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/loanapp")
public class LoanController {
	@Autowired
	private LoanService loanservice;
	
	@GetMapping("/loans")
	public ResponseEntity<PageResponseDto<Loan>> getAllLoans(@RequestParam int pageNumber, @RequestParam int pageSize) {
		
		return ResponseEntity.ok(loanservice.getAllLoans(pageNumber,pageSize));
	}
	

	@GetMapping("/loans/{loanId}")
	public ResponseEntity<Optional<Loan>> getLoan(@PathVariable int loanId){
		return ResponseEntity.ok(loanservice.getLoan(loanId));
		
	}
	
	@PostMapping("/loans")
	public String addLoan(@Valid @RequestBody Loan loan) {
		loanservice.addLoan(loan);
		return "loan added successfully";
		
		
	}
	
	@PutMapping("/loans/{loanId}")
	public String updateLoan(@PathVariable int loanId, @RequestBody Loan loan) {
		loanservice.updateLoanById(loanId, loan);
		return "loan updated successfully";
		
		
	}
	

}
