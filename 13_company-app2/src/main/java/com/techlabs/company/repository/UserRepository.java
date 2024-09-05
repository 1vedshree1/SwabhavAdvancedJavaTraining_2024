package com.techlabs.company.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techlabs.company.entity.User;

public interface UserRepository extends JpaRepository<User,Integer> {

}
