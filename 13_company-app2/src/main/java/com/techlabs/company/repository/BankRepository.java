package com.techlabs.company.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techlabs.company.entity.Bank;

public interface BankRepository extends JpaRepository<Bank, Integer>{

}
