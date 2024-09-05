package com.techlabs.company.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.techlabs.company.entity.Client;



public interface ClientRepository extends JpaRepository<Client,Integer>{
	
	public Page<Client> findByCompanyName(String companyName, Pageable pageable);

}
