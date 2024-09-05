package com.techlabs.transaction.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techlabs.transaction.entity.PaymentInfo;

public interface PaymentInfoRepository extends JpaRepository<PaymentInfo,String>{

}
