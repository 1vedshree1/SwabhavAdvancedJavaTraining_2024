package com.techlabs.loan.service;

import java.util.List;

import com.techlabs.loan.entity.Payment;

public interface PaymentService {

	public List<Payment> getAllPayments();
	Payment getpayment(int paymentId);
	public void addPayment(Payment pyament);

	public void updatePaymentById(int paymentId, Payment payment);
}
