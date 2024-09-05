package com.techlabs.loan.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techlabs.loan.entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {

}
