package com.techlabs.loan.service;

import java.util.List;
import java.util.Optional;

import com.techlabs.loan.dto.PageResponseDto;
import com.techlabs.loan.entity.Customer;

public interface CustomerService {
	
	
		public PageResponseDto<Customer> getAllCustomers(int pageNumber,int pageSize );
		public Optional<Customer> getCustomer(int customerId);
		public void addCustomer(Customer customer);
        public PageResponseDto<Customer> getCustomerByName(String firstname,int PageNumber, int pageSize);
		public void updateCustomerById(int customerId, Customer customer);
		

}
