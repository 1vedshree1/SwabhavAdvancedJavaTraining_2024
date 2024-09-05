package com.techlabs.loan.repository;

import java.util.List;

import com.techlabs.loan.entity.Customer;
import com.techlabs.loan.entity.Loan;

public interface LoanRepository {
	
	public List<Loan> getAllLoans();
	Loan getLoan(int loanId);
	public void addLoan(Loan loan);

	public void updateLoanById(int loanId, Loan loan);

}
