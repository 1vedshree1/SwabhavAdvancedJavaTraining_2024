package com.techlabs.bank.service;

import com.techlabs.bank.dto.AccountDto;
import com.techlabs.bank.entity.Account;

public interface AccountService {
	
	public Account addAccount(AccountDto accountDto);
	

}
