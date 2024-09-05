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

import com.techlabs.loan.entity.Customer;
import com.techlabs.loan.service.CustomerService;

@RestController
@RequestMapping("/loanapp")
public class CustomerController {
	
	@Autowired
	CustomerService customerService;
	
	@GetMapping("/customers")
	public ResponseEntity<List<Customer>> getAllCustomers(){
		return ResponseEntity.ok(customerService.getAllCustomers());
		
	}
	
	@GetMapping("/customers/{customerId}")
	public ResponseEntity<Customer> getCustomer(@PathVariable int customerId){
		return ResponseEntity.ok(customerService.getCustomer(customerId));
		
	} 
	
	@PostMapping("/customers")
	public String addCustomer(@RequestBody Customer customer) {
		customerService.addCustomer(customer);
		return "customer added successfully";
	}
	
	@PutMapping("/customers/{customerId}")
	public String updateCustomer(@PathVariable int customerId, @RequestBody Customer customer) {
		customerService.updateCustomerById(customerId, customer);
		return "customer updated successfully";
	}
	

}
