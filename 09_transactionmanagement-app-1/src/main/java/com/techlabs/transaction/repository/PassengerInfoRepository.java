package com.techlabs.transaction.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techlabs.transaction.entity.PassengerInfo;

public interface PassengerInfoRepository extends JpaRepository<PassengerInfo,Long> {

}
