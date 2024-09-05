package com.techlabs.company.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techlabs.company.dto.BankDto;
import com.techlabs.company.dto.ClientDto;
import com.techlabs.company.service.BankService;

@RestController
@RequestMapping("/companyapp")
public class BankController {
	@Autowired
	BankService bankservice;
	
	@GetMapping("/banks")
	public ResponseEntity<List<BankDto>> getAllBank(){
		return ResponseEntity.ok(bankservice.getAllBanks());	
		
	}
	@GetMapping("banks/{bankId}")
	public BankDto getClientById(@PathVariable int bankId) {
		return bankservice.getBankByid(bankId);
		
	}
	
	@PostMapping("/banks")
	public ResponseEntity<BankDto> addbanks(@RequestBody BankDto bankDto) {
		return ResponseEntity.ok(bankservice.addBank(bankDto));
		
		
	}
	
	
	

}
