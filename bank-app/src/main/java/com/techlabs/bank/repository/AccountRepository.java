package com.techlabs.bank.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techlabs.bank.entity.Account;

public interface AccountRepository extends JpaRepository<Account,Integer> {
	Optional<Account> findByAccountNumber(Long accountNumber);


}
