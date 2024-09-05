package com.techlabs.transaction.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techlabs.transaction.controller.FlightServiceExampleApplication;

import com.techlabs.transaction.dto.FlightBookingAcknowledgement;
import com.techlabs.transaction.dto.FlightBookingRequest;
import com.techlabs.transaction.entity.PassengerInfo;
import com.techlabs.transaction.entity.PaymentInfo;
import com.techlabs.transaction.repository.PassengerInfoRepository;
import com.techlabs.transaction.repository.PaymentInfoRepository;

import jakarta.transaction.Transactional;

@Service
public class FlightBookingService {
	 @Autowired
	    private PassengerInfoRepository passengerInfoRepository;
	    @Autowired
	    private PaymentInfoRepository paymentInfoRepository;

	    @Transactional//(readOnly = false,isolation = Isolation.READ_COMMITTED,propagation = Propagation.REQUIRED)
	    public FlightBookingAcknowledgement bookFlightTicket(FlightBookingRequest request) {

	        PassengerInfo passengerInfo = request.getPassengerInfo();
	        passengerInfo = passengerInfoRepository.save(passengerInfo);

	        PaymentInfo paymentInfo = request.getPaymentInfo();

//	        PaymentUtils.validateCreditLimit(paymentInfo.getAccountNo(), passengerInfo.getFare());

	        paymentInfo.setPassengerId(passengerInfo.getPId());
	        paymentInfo.setAmount(passengerInfo.getFare());
	        paymentInfoRepository.save(paymentInfo);
	        return new FlightBookingAcknowledgement("SUCCESS", passengerInfo.getFare(), UUID.randomUUID().toString().split("-")[0], passengerInfo);

	    }

}
