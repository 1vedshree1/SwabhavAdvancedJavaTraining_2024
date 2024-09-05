package com.techlabs.transaction.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techlabs.transaction.entity.Address;

public interface AddressRepository extends JpaRepository<Address, Integer>{

}
