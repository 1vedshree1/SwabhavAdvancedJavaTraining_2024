package com.techlabs.loan.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techlabs.loan.entity.Customer;
import com.techlabs.loan.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService{
	
	@Autowired
	CustomerRepository customerRepo;

	@Override
	public List<Customer> getAllCustomers() {
		// TODO Auto-generated method stub
		return customerRepo.getAllCustomers();
	}

	@Override
	public Customer getCustomer(int customerId) {
		// TODO Auto-generated method stub
		return customerRepo.getCustomer(customerId);
	}

	@Override
	public void addCustomer(Customer customer) {
		customerRepo.addCustomer(customer);
		
	}

	@Override
	public void updateCustomerById(int customerId, Customer customer) {
		customerRepo.updateCustomerById(customerId, customer);
		
	}
	
	

}
