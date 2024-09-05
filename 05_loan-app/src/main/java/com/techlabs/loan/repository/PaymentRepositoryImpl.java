package com.techlabs.loan.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.techlabs.loan.entity.Payment;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
public class PaymentRepositoryImpl implements PaymentRepository{

	
	@Autowired
	EntityManager manager;
	
	@Override
	public List<Payment> getAllPayments() {
		TypedQuery<Payment> query = manager.createQuery("select p from Payment p", Payment.class);
		return query.getResultList();
	}

	@Override
	public Payment getpayment(int paymentId) {
		
		return manager.find(Payment.class, paymentId);
	}
	@Transactional
	@Override
	public void addPayment(Payment pyament) {
		manager.persist(pyament);
		
		
	}
	
	@Transactional
	@Override
	public void updatePaymentById(int paymentId, Payment payment) {
		
		Payment expayment = manager.find(Payment.class, paymentId);
		
		expayment.setAmount(payment.getAmount());
		expayment.setPaymentDate(payment.getPaymentDate());
		expayment.setPaymentMode(payment.getPaymentMode());
		expayment.setPaymentStatus(payment.getPaymentStatus());
		
		manager.merge(expayment);
		
	}

}
