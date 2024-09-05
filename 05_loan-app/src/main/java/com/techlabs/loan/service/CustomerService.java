package com.techlabs.loan.service;

import java.util.List;

import com.techlabs.loan.entity.Customer;

public interface CustomerService {
	
	
		public List<Customer> getAllCustomers();
		Customer getCustomer(int customerId);
		public void addCustomer(Customer customer);

		public void updateCustomerById(int customerId, Customer customer);
		

}
