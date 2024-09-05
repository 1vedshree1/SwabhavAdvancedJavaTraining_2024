package com.techlabs.company.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techlabs.company.entity.Client;

public interface ClientRepository extends JpaRepository<Client, Integer> {

}
