package com.techlabs.loan.repository;

import java.util.List;

import com.techlabs.loan.entity.Customer;



public interface CustomerRepository {
	
	public List<Customer> getAllCustomers();
	Customer getCustomer(int customerId);
	public void addCustomer(Customer customer);

	public void updateCustomerById(int customerId, Customer customer);
	

}
