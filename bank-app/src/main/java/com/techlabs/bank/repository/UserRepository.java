package com.techlabs.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techlabs.bank.entity.User;

public interface UserRepository extends JpaRepository<User,Integer>{

}
