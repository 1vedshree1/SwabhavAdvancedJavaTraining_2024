package com.techlabs.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techlabs.bank.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer,Integer>{


}
