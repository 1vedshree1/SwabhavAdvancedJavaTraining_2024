package com.techlabs.bank.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.techlabs.bank.entity.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction,Integer>{

	Page<Transaction> findByAccount_Customer_CustomerId(int customerId, Pageable pageable);

	List<Transaction> findByAccount_AccountNumber(long accountNumber);

}
