package com.techlabs.loan.service;

import java.util.List;
import java.util.Optional;

import com.techlabs.loan.dto.PageResponseDto;
import com.techlabs.loan.entity.Payment;

public interface PaymentService {

	public PageResponseDto<Payment> getAllPayments(int pageNumber, int pageSize);
	Optional<Payment> getpayment(int paymentId);
	public void addPayment(Payment pyament);

	public void updatePaymentById(int paymentId, Payment payment);
}
