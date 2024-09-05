package com.techlabs.company.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techlabs.company.entity.Salary;

public interface SalaryRepository extends JpaRepository<Salary, Integer>{

}
