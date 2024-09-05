package com.techlabs.bank.dto;

import java.util.Date;

import com.techlabs.bank.entity.Account;
import com.techlabs.bank.entity.TransactionType;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class TransactionDto {
	
	private int transactionId;
	
	private String transactionType;
	@PositiveOrZero(message = "amount must be positive")
	private double amount;
	
	private Long AccountNumber;
	private long transferAccountNumber;
	
	private Date date;


}
