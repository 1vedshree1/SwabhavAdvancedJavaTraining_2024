package com.techlabs.bank.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techlabs.bank.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer,Integer>{
	 boolean existsByFirstName(String firstName);

	Optional<Customer> findByFirstName(String firstName);


}
