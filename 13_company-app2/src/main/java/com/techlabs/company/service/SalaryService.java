package com.techlabs.company.service;

import java.util.Optional;

import com.techlabs.company.dto.PageResponseDto;
import com.techlabs.company.entity.Salary;
import com.techlabs.company.entity.Transaction;

public interface SalaryService {
	
	public PageResponseDto<Salary> getAllSalaries(int pageNumber, int pageSize);
	public Optional<Salary> getSalaryById(int salaryId);
	public Optional<Transaction> getSalaryTransaction(int salaryId);
	public void addSalary(Salary salary);
	public Transaction updateSalaryTransaction(int salaryId, Transaction transaction);
	

}
