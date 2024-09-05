package com.techlabs.security.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techlabs.security.entity.Customer;
import com.techlabs.security.service.CustomerService;

@RestController
@RequestMapping("/app")
public class CustomerController {
	@Autowired
	private CustomerService customerService;

	@GetMapping("/customers")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<List<Customer>> getAllCustomers() {
		List<Customer> customers = customerService.getAllCustomers();
		return ResponseEntity.ok(customers);
	}

	@GetMapping("/{id}")
	@PreAuthorize("hasRole('CUSTOMER')")
	public ResponseEntity<Customer> getCustomerById(@PathVariable("id") int customerId) {
		Customer customer = customerService.getCustomerById(customerId);
		return ResponseEntity.ok(customer);
	}
}