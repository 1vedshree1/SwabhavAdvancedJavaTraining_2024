package com.techlabs.loan.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="payment")
public class Payment {
	@Column(name="paymentid")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int paymentId;
	@Column(name="paymentdate")
	private LocalDate paymentDate;
	@Column(name="amount")
	private double amount;
	@Column(name="paymentmode")
	@Enumerated(EnumType.STRING)
	private PaymentModeType paymentMode;
	@Column(name="paymentstatus")
	@Enumerated(EnumType.STRING)
	private PaymentStatusType paymentStatus;
	
	public Payment() {
		
	}
	public Payment(int paymentId, LocalDate paymentDate, double amount, PaymentModeType paymentMode,
			PaymentStatusType paymentStatus) {
		super();
		this.paymentId = paymentId;
		this.paymentDate = paymentDate;
		this.amount = amount;
		this.paymentMode = paymentMode;
		this.paymentStatus = paymentStatus;
	}
	public int getPaymentId() {
		return paymentId;
	}
	public void setPaymentId(int paymentId) {
		this.paymentId = paymentId;
	}
	public LocalDate getPaymentDate() {
		return paymentDate;
	}
	public void setPaymentDate(LocalDate paymentDate) {
		this.paymentDate = paymentDate;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public PaymentModeType getPaymentMode() {
		return paymentMode;
	}
	public void setPaymentMode(PaymentModeType paymentMode) {
		this.paymentMode = paymentMode;
	}
	public PaymentStatusType getPaymentStatus() {
		return paymentStatus;
	}
	public void setPaymentStatus(PaymentStatusType paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	

}
