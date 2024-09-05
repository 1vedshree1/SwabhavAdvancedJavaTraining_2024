package com.techlabs.bank.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.techlabs.bank.dto.PageResponseDto;
import com.techlabs.bank.dto.TransactionDto;
import com.techlabs.bank.entity.Account;
import com.techlabs.bank.entity.Transaction;
import com.techlabs.bank.entity.TransactionType;
import com.techlabs.bank.repository.AccountRepository;
import com.techlabs.bank.repository.TransactionRepository;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepo;
    @Autowired
    private AccountRepository accountRepo;

    @Transactional
    @Override
    public void addTransaction(String transactionType, double amount, Long transferAccountNumber, Long fromAccountNumber) {
        Account fromAccount = accountRepo.findByAccountNumber(fromAccountNumber)
                .orElseThrow(() -> new IllegalArgumentException("Source account not found"));

        Account transferAccount = (transactionType.equalsIgnoreCase("TRANSFER"))
                ? accountRepo.findByAccountNumber(transferAccountNumber)
                    .orElseThrow(() -> new IllegalArgumentException("Transfer account not found"))
                : null;

        switch (transactionType.toUpperCase()) {
            case "DEBIT":
                debit(fromAccount, amount);
                break;
            case "CREDIT":
                credit(fromAccount, amount);
                break;
            case "TRANSFER":
                if (transferAccount == null) {
                    throw new IllegalArgumentException("Transfer account number must be provided for transfers");
                }
                transfer(fromAccount, transferAccount, amount);
                break;
            default:
                throw new IllegalArgumentException("Invalid transaction type");
        }
    }

    private void debit(Account account, double amount) {
        if (account.getBalance() < amount) {
            throw new IllegalArgumentException("Insufficient funds");
        }
        account.setBalance(account.getBalance() - amount);
        accountRepo.save(account);

        Transaction transaction = new Transaction();
        transaction.setAccount(account);
        transaction.setTransactionType(TransactionType.DEBIT);
        transaction.setAmount(amount);
        transaction.setDate(new Date());

        transactionRepo.save(transaction);
    }

    private void credit(Account account, double amount) {
        account.setBalance(account.getBalance() + amount);
        accountRepo.save(account);

        Transaction transaction = new Transaction();
        transaction.setAccount(account);
        transaction.setTransactionType(TransactionType.CREDIT);
        transaction.setAmount(amount);
        transaction.setDate(new Date());

        transactionRepo.save(transaction);
    }

    private void transfer(Account fromAccount, Account toAccount, double amount) {
        if (fromAccount.getBalance() < amount) {
            throw new IllegalArgumentException("Insufficient funds");
        }

        fromAccount.setBalance(fromAccount.getBalance() - amount);
        accountRepo.save(fromAccount);

        toAccount.setBalance(toAccount.getBalance() + amount);
        accountRepo.save(toAccount);

        Transaction debitTransaction = new Transaction();
        debitTransaction.setAccount(fromAccount);
        debitTransaction.setTransactionType(TransactionType.DEBIT);
        debitTransaction.setAmount(amount);
        debitTransaction.setDate(new Date());

        transactionRepo.save(debitTransaction);

        Transaction creditTransaction = new Transaction();
        creditTransaction.setAccount(toAccount);
        creditTransaction.setTransactionType(TransactionType.CREDIT);
        creditTransaction.setAmount(amount);
        creditTransaction.setDate(new Date());

        transactionRepo.save(creditTransaction);
    }
    
    @Transactional
    @Override
    public PageResponseDto<TransactionDto> getAllTransactions(int pageNumber, int pageSize) {
        
        Pageable pageable = PageRequest.of(pageNumber, pageSize);

       
        Page<Transaction> transactionPage = transactionRepo.findAll(pageable);

        List<TransactionDto> transactionDtoList = new ArrayList<>();

       
        for (Transaction transaction : transactionPage.getContent()) {
            TransactionDto transactionDto = toTransactionDtoMapper(transaction);
            transactionDtoList.add(transactionDto);
        }

        
        PageResponseDto<TransactionDto> transactionPageDto = new PageResponseDto<>();
        transactionPageDto.setTotalPages(transactionPage.getTotalPages());
        transactionPageDto.setTotalElements(transactionPage.getTotalElements());
        transactionPageDto.setSize(transactionPage.getSize());
        transactionPageDto.setContent(transactionDtoList);
        transactionPageDto.setIslastPage(transactionPage.isLast());

        return transactionPageDto;
    }

    @Override
	public PageResponseDto<TransactionDto> getTransactionsByCustomerId(int customerId, int pageNumber, int pageSize) {
    	Pageable pageable = PageRequest.of(pageNumber, pageSize);

        
        Page<Transaction> transactionPage = transactionRepo.findByAccount_Customer_CustomerId(customerId, pageable);

        
        List<TransactionDto> transactionDtoList = new ArrayList<>();

        
        for (Transaction transaction : transactionPage.getContent()) {
            TransactionDto transactionDto = toTransactionDtoMapper(transaction);
            transactionDtoList.add(transactionDto);
        }

        PageResponseDto<TransactionDto> transactionPageDto = new PageResponseDto<>();
        transactionPageDto.setTotalPages(transactionPage.getTotalPages());
        transactionPageDto.setTotalElements(transactionPage.getTotalElements());
        transactionPageDto.setSize(transactionPage.getSize());
        transactionPageDto.setContent(transactionDtoList);
        transactionPageDto.setIslastPage(transactionPage.isLast());

        return transactionPageDto;
	}
    
    private TransactionDto toTransactionDtoMapper(Transaction transaction) {
        return new TransactionDto(
            transaction.getTransactionId(),
            transaction.getTransactionType().toString(), 
            transaction.getAmount(),
            transaction.getAccount().getAccountNumber(),
            transaction.getTransferAccountNumber(),
            transaction.getDate()
        );
    }

	
}

