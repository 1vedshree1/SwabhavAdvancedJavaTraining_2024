package com.techlabs.loan.service;

import java.util.List;

import com.techlabs.loan.entity.Loan;

public interface LoanService {
	public List<Loan> getAllLoans();
	Loan getLoan(int loanId);
	public void addLoan(Loan loan);

	public void updateLoanById(int loanId, Loan loan);

}
