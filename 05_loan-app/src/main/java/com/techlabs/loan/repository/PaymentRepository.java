package com.techlabs.loan.repository;

import java.util.List;


import com.techlabs.loan.entity.Payment;

public interface PaymentRepository {
	
	public List<Payment> getAllPayments();
	Payment getpayment(int paymentId);
	public void addPayment(Payment pyament);

	public void updatePaymentById(int paymentId, Payment payment);

}
