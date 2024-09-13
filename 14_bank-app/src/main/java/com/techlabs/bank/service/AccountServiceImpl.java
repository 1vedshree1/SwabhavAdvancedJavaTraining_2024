package com.techlabs.bank.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techlabs.bank.dto.AccountDto;
import com.techlabs.bank.entity.Account;
import com.techlabs.bank.entity.Customer;
import com.techlabs.bank.exception.CustomerNotFoundException;
import com.techlabs.bank.repository.AccountRepository;
import com.techlabs.bank.repository.CustomerRepository;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	AccountRepository accountRepo;
	@Autowired
	CustomerService customerService;
	@Autowired
	EmailNotificationService emailNotification;

	@Override
	public Account addAccount(AccountDto accountDto) {
		Customer customer = customerService.getCustomerById(accountDto.getCustomerId());
		if (customer == null) {
            throw new CustomerNotFoundException("Customer with ID " + accountDto.getCustomerId() + " not found.");
        }
		Account account = new Account();
		account.setAccountNumber(generateAccountNumber());
		account.setAccountType(accountDto.getAccountType());
		account.setBalance(accountDto.getBalance());
		account.setCustomer(customer);
		account = accountRepo.save(account);
		
		String message= String.format("Congratulations, Your account has been created successfully, your account numbr is "+ account.getAccountNumber());
        String recipientEmail = customer.getEmail();
        String subject="Account Created";
        emailNotification.sendNotification(recipientEmail, message, subject);
		return account;

	}

	private long generateAccountNumber() {
		long min = 10000000000L;
		long max = 99999999999L;
		return min + (long) (Math.random() * (max - min + 1));

	}

}
