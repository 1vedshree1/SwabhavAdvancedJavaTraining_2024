package com.techlabs.bank.entity;

import java.util.Date;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name="transactions")
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Transaction {
	@Column(name="transactionId")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int transactionId;
	@ManyToOne(cascade = {CascadeType.MERGE,CascadeType.REFRESH,CascadeType.DETACH})
	@JoinColumn(name="accountId")
	private Account account;
	@Column(name="transactionType")
	private TransactionType transactionType;
	@Column(name="amount")
	@PositiveOrZero(message = "amount must be positive")
	private double amount;
	@Column(name="transferAccountNumber",length=12)
	private long transferAccountNumber;
	@Column(name="date")
	private Date date;

}
