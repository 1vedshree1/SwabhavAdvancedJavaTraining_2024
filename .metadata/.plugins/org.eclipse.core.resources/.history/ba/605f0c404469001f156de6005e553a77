package com.techlabs.bank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techlabs.bank.dto.AccountDto;
import com.techlabs.bank.entity.Account;
import com.techlabs.bank.service.AccountService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/bankapp")
public class AccountController {
	
	@Autowired
	private AccountService accountService;
	
	@PostMapping("/accounts")
	public String addAccount(@Valid @RequestBody AccountDto accountDto) {
		accountService.addAccount(accountDto);
		return "account added successfully";
	}

}
