package com.techlabs.loan.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techlabs.loan.entity.Loan;
import com.techlabs.loan.service.LoanService;

@RestController
@RequestMapping("/loanapp")
public class LoanController {
	
	@Autowired 
	LoanService loanService;
	
	@GetMapping("/loans")
	public ResponseEntity<List<Loan>> getAllLoans(){
		return ResponseEntity.ok(loanService.getAllLoans());
		
	}
	
	@GetMapping("/loans/{loanId}")
	public ResponseEntity<Loan> getLoan(@PathVariable int loanId){
		return ResponseEntity.ok(loanService.getLoan(loanId));
		
	}
	
	@PostMapping("/loans")
	public String addLoan(@RequestBody Loan loan) {
		loanService.addLoan(loan);
		return "loan added successfully";
		
		
	}
	
	@PutMapping("/loans/{loanId}")
	public String updateLoan(@PathVariable int loanId, @RequestBody Loan loan) {
		loanService.updateLoanById(loanId, loan);
		return "loan updated successfully";
		
		
	}
	

}
