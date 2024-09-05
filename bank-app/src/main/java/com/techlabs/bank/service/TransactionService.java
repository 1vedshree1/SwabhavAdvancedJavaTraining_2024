package com.techlabs.bank.service;

import com.techlabs.bank.dto.PageResponseDto;
import com.techlabs.bank.dto.TransactionDto;
import com.techlabs.bank.entity.Account;
import com.techlabs.bank.entity.TransactionType;

public interface TransactionService {
	
//	public void debit(Account account, double amount);
//	public void credit(Account account, double amount);
//	public void transfer(Account fromAccount, Account toAccount,double amount);
	void addTransaction(String transactionType, double amount, Long transferAccountNumber, Long accountNumber);

	PageResponseDto<TransactionDto> getAllTransactions(int pageNumber, int pageSize);

//	PageResponseDto<TransactionDto> getTransactionsByAccountId(int accountId, int pageNumber, int pageSize);

	PageResponseDto<TransactionDto> getTransactionsByCustomerId(int customerId, int pageNumber, int pageSize);

}
