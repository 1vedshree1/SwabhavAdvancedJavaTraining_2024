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
import com.techlabs.loan.service.CustomerService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/loanapp")
public class CustomerController {
	@Autowired 
	CustomerService customerservice;
	
	@GetMapping("/customers")
	public ResponseEntity<PageResponseDto<Customer>> getAllCustomers(@RequestParam(required = false)String firstname,@RequestParam int pageNumber, @RequestParam int pageSize) {
		if(firstname!=null) 
			return ResponseEntity.ok(customerservice.getCustomerByName(firstname,pageNumber, pageSize ));
		
		return ResponseEntity.ok(customerservice.getAllCustomers( pageNumber,pageSize));
	}
	
	@GetMapping("/customers/{customerId}")
	public ResponseEntity<Optional<Customer>> getCustomer(@PathVariable int customerId){
		return ResponseEntity.ok(customerservice.getCustomer(customerId));
		
	} 
	
	@PostMapping("/customers")
	public String addCustomer(@Valid @RequestBody Customer customer) {
		customerservice.addCustomer(customer);
		return "customer added successfully";
	}
	
	@PutMapping("/customers/{customerId}")
	public String updateCustomer(@PathVariable int customerId, @RequestBody Customer customer) {
		customerservice.updateCustomerById(customerId, customer);
		return "customer updated successfully";
	}
	
	

}
