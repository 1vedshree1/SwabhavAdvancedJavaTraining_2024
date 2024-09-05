package com.techlabs.loan.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techlabs.loan.entity.Payment;
import com.techlabs.loan.repository.PaymentRepository;

@Service
public class PaymentServiceImpl implements PaymentService{
	
	@Autowired
	PaymentRepository paymentRepo;

	@Override
	public List<Payment> getAllPayments() {
		
		return paymentRepo.getAllPayments();
	}

	@Override
	public Payment getpayment(int paymentId) {
		// TODO Auto-generated method stub
		return paymentRepo.getpayment(paymentId);
	}

	@Override
	public void addPayment(Payment pyament) {
		paymentRepo.addPayment(pyament);
		
	}

	@Override
	public void updatePaymentById(int paymentId, Payment payment) {
		paymentRepo.updatePaymentById(paymentId, payment);
		
	}

}
