package com.techlabs.bank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.techlabs.bank.dto.PageResponseDto;
import com.techlabs.bank.dto.TransactionDto;
import com.techlabs.bank.service.TransactionService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/bankapp")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping("/transaction")
    public ResponseEntity<String> performTransaction(@Valid @RequestBody TransactionDto transactionDto) {
        try {
            Long fromAccountNumber = transactionDto.getFromAccountNumber();
            Long transferAccountNumber = transactionDto.getTransferAccountNumber();  
            String transactionType = transactionDto.getTransactionType();
            double amount = transactionDto.getAmount();

            transactionService.addTransaction(transactionType, amount, transferAccountNumber, fromAccountNumber);

            return ResponseEntity.ok("Transaction successful");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Transaction failed: " + e.getMessage());
        }
    }
    @GetMapping("/transaction")
    public ResponseEntity<PageResponseDto<TransactionDto>> getAllTransactions(@RequestParam int pageNumber, @RequestParam int pageSize){
		return ResponseEntity.ok(transactionService.getAllTransactions(pageNumber, pageSize));
    	
    }
    @GetMapping("/transaction/{customerId}")
    public ResponseEntity<PageResponseDto<TransactionDto>> getTransactionsByAccountId(@PathVariable int customerId,
        @RequestParam int pageNumber,
        @RequestParam int pageSize) {
        
            PageResponseDto<TransactionDto> transactions = transactionService.getTransactionsByCustomerId(customerId, pageNumber, pageSize);
            return ResponseEntity.ok(transactions);
        
    }
}
