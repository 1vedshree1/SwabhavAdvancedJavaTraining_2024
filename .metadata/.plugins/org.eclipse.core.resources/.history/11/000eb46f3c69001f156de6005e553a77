package com.techlabs.bank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techlabs.bank.dto.CustomerDto;
import com.techlabs.bank.dto.PageResponseDto;
import com.techlabs.bank.dto.ProfileDto;
import com.techlabs.bank.entity.Customer;
import com.techlabs.bank.service.CustomerService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/bankapp")
public class CustomerController {
	@Autowired
	private CustomerService customerService;
	
	@GetMapping("/customers")
	public ResponseEntity<PageResponseDto<CustomerDto>> getAllCustomers(int pageNumber, int pageSize){
		return ResponseEntity.ok(customerService.getAllCustomers(pageNumber, pageSize));		
	}
	@PostMapping("/customers")
	public ResponseEntity<CustomerDto> addCustomers(@Valid @RequestBody CustomerDto customerDto){
		return ResponseEntity.ok(customerService.addCustomer(customerDto));
		
	}
	@PutMapping("/customers/{customerId}")
	public ResponseEntity<CustomerDto> updateProfile(@PathVariable int customerId, @RequestBody ProfileDto profile){
		return ResponseEntity.ok(customerService.updateCustomer(customerId, profile));
		
	}

}
