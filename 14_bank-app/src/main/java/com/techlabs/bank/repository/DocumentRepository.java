package com.techlabs.bank.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techlabs.bank.entity.Document;

public interface DocumentRepository extends JpaRepository<Document,Integer>{
	
	 List<Document> findByCustomer_CustomerId(int customerId);

}
