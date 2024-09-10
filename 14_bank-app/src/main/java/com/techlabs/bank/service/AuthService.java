package com.techlabs.bank.service;

import com.techlabs.bank.dto.CustomerDto;
import com.techlabs.bank.dto.LoginDto;
import com.techlabs.bank.dto.RegistrationDto;
import com.techlabs.bank.entity.Customer;
import com.techlabs.bank.entity.User;

public interface AuthService {

	User register(RegistrationDto registration, String captchaResponse);
	String login(LoginDto loginDto, String captchaResponse);
	Customer addCustomer(CustomerDto customerDto);
}
