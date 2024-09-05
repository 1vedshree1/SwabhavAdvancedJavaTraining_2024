package com.techlabs.loan.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.techlabs.loan.dto.PageResponseDto;
import com.techlabs.loan.entity.Customer;
import com.techlabs.loan.entity.Loan;
import com.techlabs.loan.repository.LoanRepository;


@Service
public class LoanServiceImpl implements LoanService{

	@Autowired
	private LoanRepository loanRepo;
	@Override
	public PageResponseDto<Loan> getAllLoans(int pageNumber, int pageSize) {
		Pageable pageable = PageRequest.of(pageNumber,pageSize);
		
		Page<Loan> loanPage = loanRepo.findAll(pageable);
		PageResponseDto<Loan> loanPageDto = new PageResponseDto<Loan>();
		loanPageDto.setTotalPages(loanPage.getTotalPages());
		loanPageDto.setTotalElements(loanPage.getTotalElements());
		loanPageDto.setContent(loanPage.getContent());
		loanPageDto.setIslastPage(loanPage.isLast());
		
		
		
		
		return loanPageDto;
	}

	@Override
	public Optional<Loan> getLoan(int loanId) {
		// TODO Auto-generated method stub
		return loanRepo.findById(null);
	}

	@Override
	public void addLoan(Loan loan) {
		loanRepo.save(loan);
		
	}

	@Override
	public void updateLoanById(int loanId, Loan loan) {
		
		loan.setLoanId(loanId);
		loanRepo.save(loan);
		
	}
	

	
	

}
