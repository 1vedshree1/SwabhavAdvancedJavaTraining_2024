package com.techlabs.company.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techlabs.company.dto.BankDto;
import com.techlabs.company.entity.Bank;
import com.techlabs.company.repository.BankRepository;

@Service
public class BankServiceImpl implements BankService{
	
	@Autowired
	private BankRepository bankRepo;

	@Override
	public BankDto addBank(BankDto bankDto) {
		Bank bank = toBankMapper(bankDto);
		bank = bankRepo.save(bank);
		
		return toBankDtoMapper(bank);
	}

	@Override
	public BankDto getBankByid(int bankId) {
		Optional<Bank> optionalBank = bankRepo.findById(bankId);
		if(!optionalBank.isPresent())
			return null;
		Bank bankdb = optionalBank.get();
		
		
		return toBankDtoMapper(bankdb);
	}

	@Override
	public List<BankDto> getAllBanks() {
		List<Bank> bankList = bankRepo.findAll();
		List<BankDto> bankDtoList = new ArrayList<BankDto>();
		BankDto bankDto = new BankDto();
		for(Bank bank:bankList) {
			bankDto = toBankDtoMapper(bank);
			bankDtoList.add(bankDto);
		}
		return bankDtoList;
	}
	
	
	public BankDto toBankDtoMapper(Bank bank) {
		BankDto bankDto = new BankDto();
		bankDto.setBankId(bank.getBankId());
		bankDto.setBankName(bank.getBankName());
		bankDto.setBranch(bank.getBranch());
		bankDto.setIfscCode(bank.getIfscCode());
		return bankDto;
	}
	public Bank toBankMapper(BankDto bankDto) {
		
		Bank bank = new Bank();
		bank.setBankId(bankDto.getBankId());
		bank.setBankName(bankDto.getBankName());
		bank.setBranch(bankDto.getBranch());
		bank.setIfscCode(bankDto.getIfscCode());
		return bank;
		
	}

}
