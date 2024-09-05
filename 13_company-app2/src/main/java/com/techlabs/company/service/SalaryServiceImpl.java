package com.techlabs.company.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.techlabs.company.dto.PageResponseDto;
import com.techlabs.company.entity.Salary;
import com.techlabs.company.entity.Transaction;
import com.techlabs.company.repository.SalaryRepository;

@Service
public class SalaryServiceImpl implements SalaryService{

	@Autowired
	private SalaryRepository salaryRepo;
	
	@Override
	public PageResponseDto<Salary> getAllSalaries(int pageNumber, int pageSize) {
		
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		Page<Salary> salaryPage = salaryRepo.findAll(pageable);
		PageResponseDto salaryPageDto = new PageResponseDto();
		
		salaryPageDto.setTotalPages(salaryPage.getTotalPages());
		salaryPageDto.setTotalElements(salaryPage.getTotalElements());
		salaryPageDto.setContent(salaryPage.getContent());
		salaryPageDto.setIslastPage(salaryPage.isLast());
		
		// TODO Auto-generated method stub
		return salaryPageDto;
	}

	@Override
	public Optional<Salary> getSalaryById(int salaryId) {
		// TODO Auto-generated method stub
		return salaryRepo.findById(salaryId);
	}

	@Override
	public Optional<Transaction> getSalaryTransaction(int salaryId) {
		
		Optional<Salary> salarydb = salaryRepo.findById(salaryId);
		// TODO Auto-generated method stub
		return salarydb.map(Salary:: getTransaction);
	}

	@Override
	public void addSalary(Salary salary) {
		
		salaryRepo.save(salary);
	}

	@Override
	public Transaction updateSalaryTransaction(int salaryId, Transaction transaction) {
		Salary salarydb = null;
		Optional<Salary>optionalsalary= salaryRepo.findById(salaryId);
		if(!optionalsalary.isPresent())
			return null;
		salarydb = optionalsalary.get();
		Transaction transactiondb = salarydb.getTransaction();
		
		transactiondb.setTransactionStatus(transaction.getTransactionStatus());
		
		return salaryRepo.save(salarydb).getTransaction();
	}

}
