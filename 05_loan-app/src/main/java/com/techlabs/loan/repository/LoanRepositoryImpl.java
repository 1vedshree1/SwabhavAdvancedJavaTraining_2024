package com.techlabs.loan.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.techlabs.loan.entity.Customer;
import com.techlabs.loan.entity.Loan;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
public class LoanRepositoryImpl implements LoanRepository{
	
	
	@Autowired
	EntityManager manager;

	@Override
	public List<Loan> getAllLoans() {
		
		TypedQuery<Loan> query = manager.createQuery("select l from Loan l", Loan.class);
		
		return query.getResultList();
	}

	@Override
	public Loan getLoan(int loanId) {
		
		
		return manager.find(Loan.class, loanId);
	}

	@Transactional
	@Override
	public void addLoan(Loan loan) {
		manager.persist(loan);
		
	}
	
	
	@Transactional
	@Override
	public void updateLoanById(int loanId, Loan loan) {
		
		Loan exloan = manager.find(Loan.class, loanId);
		
		exloan.setLoanAmount(loan.getLoanAmount());
		exloan.setInterestRate(loan.getInterestRate());
		exloan.setLoanTerm(loan.getLoanTerm());
		exloan.setStartDate(loan.getStartDate());
		exloan.setEndDate(loan.getEndDate());
		exloan.setLoanStatus(loan.getLoanStatus());
		
		manager.merge(exloan);
		
		
		
	}
	
	

}
