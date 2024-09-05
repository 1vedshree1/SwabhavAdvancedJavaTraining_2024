package com.techlabs.loan.service;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.techlabs.loan.dto.PageResponseDto;

import com.techlabs.loan.entity.Payment;
import com.techlabs.loan.repository.PaymentRepository;

@Service
public class PaymentServiceImpl implements PaymentService{
	
	@Autowired
	PaymentRepository paymentRepo;

	@Override
	public PageResponseDto<Payment> getAllPayments(int pageNumber, int pageSize) {
		Pageable pageable = PageRequest.of(pageNumber,pageSize);
		
		Page<Payment> paymentPage = paymentRepo.findAll(pageable);
		PageResponseDto<Payment> paymentPageDto = new PageResponseDto<Payment>();
		paymentPageDto.setTotalPages(paymentPage.getTotalPages());
		paymentPageDto.setTotalElements(paymentPage.getTotalElements());
		paymentPageDto.setContent(paymentPage.getContent());
		paymentPageDto.setIslastPage(paymentPage.isLast());
		
		
		
		
		return paymentPageDto;
	}

	@Override
	public Optional<Payment> getpayment(int paymentId) {
		// TODO Auto-generated method stub
		return paymentRepo.findById(paymentId);
	}

	@Override
	public void addPayment(Payment payment) {
		paymentRepo.save(payment);
		
	}

	@Override
	public void updatePaymentById(int paymentId, Payment payment) {
		payment.setPaymentId(paymentId);
		paymentRepo.save(payment);
	}

}
