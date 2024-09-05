package com.techlabs.loan.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techlabs.loan.entity.Loan;
import com.techlabs.loan.repository.LoanRepository;

@Service
public class LoanServiceImpl implements LoanService{
	@Autowired
	LoanRepository loanRepo;

	@Override
	public List<Loan> getAllLoans() {
		// TODO Auto-generated method stub
		return loanRepo.getAllLoans();
	}

	@Override
	public Loan getLoan(int loanId) {
		// TODO Auto-generated method stub
		return loanRepo.getLoan(loanId);
	}

	@Override
	public void addLoan(Loan loan) {
		loanRepo.addLoan(loan);
		
	}

	@Override
	public void updateLoanById(int loanId, Loan loan) {
		loanRepo.updateLoanById(loanId, loan);
		
	}
	
	

}
