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
@Table(name="loan")
public class Loan {
	@Column(name="loanid")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int loanId;
	@Column(name="loanamount")
	private double loanAmount;
	@Column(name="interestrate")
	private float interestRate;
	@Column(name="loanterm")
	private float loanTerm;
	@Column(name="startdate")
	private LocalDate startDate;
	@Column(name="enddate")
	private LocalDate endDate;
	@Column(name="loanstatus")
	@Enumerated(EnumType.STRING)
	private LoanStatusType loanStatus;
	
	public Loan() {
		
	}
	public Loan(double loanAmount, float interestRate, float loanTerm, LocalDate startDate,
			LocalDate endDate, LoanStatusType loanStatus) {
		super();
		
		this.loanAmount = loanAmount;
		this.interestRate = interestRate;
		this.loanTerm = loanTerm;
		this.startDate = startDate;
		this.endDate = endDate;
		this.loanStatus = loanStatus;
	}
	public int getLoanId() {
		return loanId;
	}
	public void setLoanId(int loanId) {
		this.loanId = loanId;
	}
	public double getLoanAmount() {
		return loanAmount;
	}
	public void setLoanAmount(double loanAmount) {
		this.loanAmount = loanAmount;
	}
	public float getInterestRate() {
		return interestRate;
	}
	public void setInterestRate(float interestRate) {
		this.interestRate = interestRate;
	}
	public float getLoanTerm() {
		return loanTerm;
	}
	public void setLoanTerm(float loanTerm) {
		this.loanTerm = loanTerm;
	}
	public LocalDate getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
	public LocalDate getEndDate() {
		return endDate;
	}
	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}
	public LoanStatusType getLoanStatus() {
		return loanStatus;
	}
	public void setLoanStatus(LoanStatusType loanStatus) {
		this.loanStatus = loanStatus;
	}
	
	
	

}
