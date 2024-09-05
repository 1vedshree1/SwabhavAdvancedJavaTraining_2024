package com.techlabs.loan.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import com.techlabs.loan.dto.PageResponseDto;
import com.techlabs.loan.entity.Customer;
import com.techlabs.loan.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService{
	
	@Autowired
	CustomerRepository customerRepo;

	@Override
	public PageResponseDto<Customer> getAllCustomers( int pageNumber,int pageSize) {
Pageable pageable = PageRequest.of(pageNumber,pageSize);
		
		Page<Customer> customerPage = customerRepo.findAll(pageable);
		PageResponseDto<Customer> customerPageDto = new PageResponseDto<Customer>();
		customerPageDto.setTotalPages(customerPage.getTotalPages());
		customerPageDto.setTotalElements(customerPage.getTotalElements());
		customerPageDto.setContent(customerPage.getContent());
		customerPageDto.setIslastPage(customerPage.isLast());
		
		
		
		
		return customerPageDto;
	}

	@Override
	public Optional<Customer> getCustomer(int customerId) {
		
		return customerRepo.findById(customerId);
	}

	@Override
	public void addCustomer(Customer customer) {
		customerRepo.save(customer);
		
	}

	@Override
	public PageResponseDto<Customer> getCustomerByName(String firstname, int pageNumber, int pageSize) {
		
		Pageable pageable = PageRequest.of(pageNumber,pageSize);
		
		Page<Customer> customerPage = customerRepo.findByFirstName(firstname,pageable);
		PageResponseDto<Customer> customerPageDto = new PageResponseDto<Customer>();
		customerPageDto.setTotalPages(customerPage.getTotalPages());
		customerPageDto.setTotalElements(customerPage.getTotalElements());
		customerPageDto.setContent(customerPage.getContent());
		customerPageDto.setIslastPage(customerPage.isLast());
		
		
		
		
		return customerPageDto;
	}

	@Override
	public void updateCustomerById(int customerId, Customer customer) {
		customer.setCustomerId(customerId); 
		customerRepo.save(customer);
		
	}

	
}
