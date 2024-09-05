package com.techlabs.company.service;

import java.util.List;

import com.techlabs.company.dto.BankDto;

public interface BankService {
	
	public BankDto addBank(BankDto bankDto);
	BankDto getBankByid(int bankId);
	List<BankDto> getAllBanks();
	
	
	
	

}
