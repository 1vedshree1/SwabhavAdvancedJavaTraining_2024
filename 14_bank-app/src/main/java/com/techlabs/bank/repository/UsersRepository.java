package com.techlabs.bank.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techlabs.bank.entity.Users;


public interface UsersRepository extends JpaRepository<Users, Integer> {
Optional<Users> findByUserName(String username);
	
	boolean existsByUserName(String username);

}
