package com.techlabs.bank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techlabs.bank.dto.CustomerDto;
import com.techlabs.bank.dto.PageResponseDto;
import com.techlabs.bank.dto.ProfileDto;
import com.techlabs.bank.entity.Customer;
import com.techlabs.bank.service.AuthService;
import com.techlabs.bank.service.CustomerService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/bankapp")
public class CustomerController {
	@Autowired
	private CustomerService customerService;
	@Autowired
	AuthService authService;
	
	@GetMapping("/customers")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<PageResponseDto<CustomerDto>> getAllCustomers(int pageNumber, int pageSize){
		return ResponseEntity.ok(customerService.getAllCustomers(pageNumber, pageSize));		
	}
	@PostMapping("/customers")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Customer> addCustomers(@Valid @RequestBody CustomerDto customerDto){
		return ResponseEntity.ok(authService.addCustomer(customerDto));
		
	}
	@PutMapping("/customers/update")
	@PreAuthorize("hasRole('CUSTOMER')")
	public ResponseEntity<CustomerDto> updateProfile(@Valid @RequestBody ProfileDto profile,  @RequestHeader("Authorization") String authHeader){
		String token = authHeader.startsWith("Bearer ") ? authHeader.substring(7) : null;
		return ResponseEntity.ok(customerService.updateCustomer(profile,token));
		
	}

}
