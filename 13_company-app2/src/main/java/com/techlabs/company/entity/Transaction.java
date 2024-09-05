package com.techlabs.company.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="transactions")
public class Transaction {
	@Column(name="transactionId")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int transactionId;
	@Column(name="transactionDate")
	private Date transactionDate;
	@Column(name="amount")
	private double amount;
	@Column(name="transactionStatus")
	private transactionStatusType transactionStatus;

}
