package com.techlabs.bank.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techlabs.bank.entity.User;


public interface UsersRepository extends JpaRepository<User, Integer> {
Optional<User> findByUserName(String username);
	
	boolean existsByUserName(String username);

}
