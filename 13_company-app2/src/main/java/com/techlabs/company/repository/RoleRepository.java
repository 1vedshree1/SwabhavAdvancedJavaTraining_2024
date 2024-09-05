package com.techlabs.company.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techlabs.company.entity.Role;

public interface RoleRepository extends JpaRepository<Role,Integer> {
	

}
