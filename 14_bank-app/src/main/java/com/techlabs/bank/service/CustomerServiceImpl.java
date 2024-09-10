package com.techlabs.bank.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.techlabs.bank.dto.CustomerDto;
import com.techlabs.bank.dto.PageResponseDto;
import com.techlabs.bank.dto.ProfileDto;
import com.techlabs.bank.entity.Customer;
import com.techlabs.bank.entity.User;
import com.techlabs.bank.entity.UserType;
import com.techlabs.bank.entity.User;
import com.techlabs.bank.exception.CustomerNotFoundException;
import com.techlabs.bank.exception.IncorrectPasswordException;
import com.techlabs.bank.repository.CustomerRepository;
import com.techlabs.bank.repository.UserRepository;
import com.techlabs.bank.repository.UsersRepository;
import com.techlabs.bank.security.JwtAuthenticationFilter;


import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService{
	
	@Autowired
	CustomerRepository customerRepo;
	@Autowired
	UsersRepository userRepo;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private TokenBlacklistService tokenBlacklistService;

//	@Override
//	public CustomerDto addCustomer(CustomerDto customerDto) {
//		User user = new User();
//	    user.setUserName(customerDto.getFirstName());
//	    user.setUserPassword(customerDto.getPassword());
//	    user.setUserType(UserType.CUSTOMER); 
//	    
//	 
//	    User savedUser = userRepo.save(user);
//
//	   
//	    Customer customer = toCustomerMapper(customerDto);
//	    customer.setUser(savedUser); 
//		
//	    Customer savedCustomer = customerRepo.save(customer);
//		
//		return toCustomerDtoMapper(savedCustomer);
//	}
//	

	private static final Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);
	@Override
	public PageResponseDto<CustomerDto> getAllCustomers(int pageNumber, int pageSize) {
		
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		Page<Customer> customerPage = customerRepo.findAll(pageable);
		List<CustomerDto> customerDtoList = new ArrayList<CustomerDto>();
		CustomerDto customerDto = new CustomerDto();
		for(Customer customer:customerPage.getContent()) {
			customerDto = toCustomerDtoMapper(customer);
			customerDtoList.add(customerDto);	
		}
		PageResponseDto customerpageDto = new PageResponseDto();
		customerpageDto.setTotalPages(customerPage.getTotalPages());
		customerpageDto.setTotalElements(customerPage.getTotalElements());
        customerpageDto.setSize(customerPage.getSize());
        customerpageDto.setContent(customerDtoList);
        customerpageDto.setIslastPage(customerPage.isLast());
		
		
		return customerpageDto;
	}
	
	public Customer toCustomerMapper(CustomerDto customerDto) {
		
		Customer customer = new Customer();
		User user = new User();
		
		
		customer.setFirstName(customerDto.getFirstName());
		customer.setLastName(customerDto.getLastName());
		customer.setEmail(customerDto.getEmail());
		customer.setPassword(customerDto.getPassword());
		
		
		
		return customer;
			
	}
	public CustomerDto toCustomerDtoMapper(Customer customer) {
		CustomerDto customerDto = new CustomerDto();
		
		customerDto.setCustomerId(customer.getCustomerId());
		customerDto.setFirstName(customer.getFirstName());
		customerDto.setLastName(customer.getLastName());
		customerDto.setEmail(customer.getEmail());
		customerDto.setPassword(customer.getPassword());
		return customerDto;
	}

	@Override
	public Customer getCustomerById(int customerId) {
		Optional<Customer> optionalCustomer = customerRepo.findById(customerId);
		
		if (!optionalCustomer.isPresent()) {
            throw new CustomerNotFoundException("Customer with ID " + customerId + " not found.");
        }
		Customer dbcustomer = optionalCustomer.get();

		
		return dbcustomer;
	}

	@Override
	public CustomerDto updateCustomer(ProfileDto profile, String token) {
		 Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		 
   	  String firstName = authentication.getName();
   	  System.out.println(firstName);
		
		Customer customer = customerRepo.findByFirstName(firstName).orElseThrow(()-> new NullPointerException("customer not found"));
		User user = customer.getUser();
		
		 if (profile.getFirstName() != null && !profile.getFirstName().isEmpty()) {
		        customer.setFirstName(profile.getFirstName());
		        user.setUserName(profile.getFirstName());
		 } 
		    
		    
		    if (profile.getLastName() != null && !profile.getLastName().isEmpty()) {
		        customer.setLastName(profile.getLastName());
		    }
		    
		    if (profile.getEmail() != null && !profile.getEmail().isEmpty()) {
		        customer.setEmail(profile.getEmail());
		    }
		    
		    
		    if (passwordEncoder.matches(profile.getPassword(), customer.getPassword())) {
	            
	            String encodedNewPassword = passwordEncoder.encode(profile.getNewPassword());
	            customer.setPassword(encodedNewPassword);
	            user.setPassword(encodedNewPassword);
	        } else {
	            throw new IncorrectPasswordException("Current password is incorrect.");
	        }

	
	
		  userRepo.save(user);
		  logger.info("customer updated successfully.");
		    Customer updatedCustomer = customerRepo.save(customer);
		    
		    tokenBlacklistService.blacklistToken(token);
		    
		 return toCustomerDtoMapper(updatedCustomer);
	
	}
	
}
