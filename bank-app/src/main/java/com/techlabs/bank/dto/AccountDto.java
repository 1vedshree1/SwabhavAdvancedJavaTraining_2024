package com.techlabs.bank.dto;

import com.techlabs.bank.entity.AccountType;
import com.techlabs.bank.entity.Customer;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class AccountDto {
	
	
	 private int accountId;
    private int customerId;
    
    @NotNull(message = "Account Type is required")
    private AccountType accountType;
    
    @PositiveOrZero(message = "Balance must be zero or greater")
    private double balance;

}
