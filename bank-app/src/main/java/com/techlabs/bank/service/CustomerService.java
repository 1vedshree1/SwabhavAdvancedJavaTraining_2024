package com.techlabs.bank.service;

import java.util.Optional;

import com.techlabs.bank.dto.CustomerDto;
import com.techlabs.bank.dto.PageResponseDto;
import com.techlabs.bank.dto.ProfileDto;
import com.techlabs.bank.entity.Customer;

public interface CustomerService {
	
	
	public PageResponseDto<CustomerDto> getAllCustomers(int pageNumber, int pageSize);
	public CustomerDto updateCustomer(int customerId, ProfileDto profile);
	
	Customer getCustomerById(int customerId);
	CustomerDto addCustomer(CustomerDto customerDto);
	

}
