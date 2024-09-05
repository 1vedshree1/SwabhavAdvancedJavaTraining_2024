package com.techlabs.bank.entity;

import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name="accounts")
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Account {
	@Column(name="accountId")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int accountId;
	@ManyToOne(cascade= CascadeType.ALL)
	@JoinColumn(name="customerId")
	@JsonIgnore
	private Customer customer;
	@Column(name="accountNumber")
	private long accountNumber;
	@Column(name="accountType")
	private AccountType accountType;
	@Column(name="balance")
	private double balance;
	@OneToMany(mappedBy = "account",cascade = CascadeType.ALL)
	List<Transaction> transaction;

}
