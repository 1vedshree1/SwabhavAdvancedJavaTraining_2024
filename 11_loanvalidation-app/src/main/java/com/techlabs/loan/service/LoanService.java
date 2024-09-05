package com.techlabs.loan.service;

import java.util.List;
import java.util.Optional;

import com.techlabs.loan.dto.PageResponseDto;
import com.techlabs.loan.entity.Loan;

public interface LoanService {
	public PageResponseDto<Loan> getAllLoans(int pageNumber, int pageSize);
	public Optional<Loan> getLoan(int loanId);
	public void addLoan(Loan loan);

	public void updateLoanById(int loanId, Loan loan);

}
