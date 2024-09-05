package com.techlabs.bank.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
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
	@ManyToOne
	@JoinColumn(name="accountId")
	private Account account;
	@Column(name="transactionType")
	private TransactionType transactionType;
	@Column(name="amount")
	private double amount;
	@Column(name="transferAccountNumber")
	private long transferAccountNumber;
	@Column(name="date")
	private Date date;

}
