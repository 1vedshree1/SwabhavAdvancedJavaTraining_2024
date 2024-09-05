package com.techlabs.loan.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.techlabs.loan.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer>{
	
	public Page<Customer> findByFirstName(String name,Pageable pageable);

}
