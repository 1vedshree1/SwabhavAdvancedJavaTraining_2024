package com.techlabs.bank.service;

import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionException;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itextpdf.text.DocumentException;
import com.techlabs.bank.config.PdfGenerator;
import com.techlabs.bank.dto.PageResponseDto;
import com.techlabs.bank.dto.TransactionDto;
import com.techlabs.bank.entity.Account;
import com.techlabs.bank.entity.Transaction;
import com.techlabs.bank.entity.TransactionType;
import com.techlabs.bank.repository.AccountRepository;
import com.techlabs.bank.repository.CustomerRepository;
import com.techlabs.bank.repository.TransactionRepository;


@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepo;
    @Autowired
    private AccountRepository accountRepo;
    @Autowired
    private CustomerRepository customerRepo;
    @Autowired
    private EmailNotificationService emailNotification;

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job exportJob;

    private static final Logger logger = LoggerFactory.getLogger(TransactionServiceImpl.class);

    public String getEmailByUsername(String username) {
        return customerRepo.findByFirstName(username)
                           .map(customer -> customer.getEmail())
                           .orElseThrow(() -> new IllegalArgumentException("Email not found for user: " + username));
    }

    @Transactional
    @Override
    public void addTransaction(String transactionType, double amount, Long transferAccountNumber, Long fromAccountNumber) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName(); 
        
        List<Account> userAccounts = accountRepo.findByCustomer_FirstName(username);

        Account fromAccount = userAccounts.stream()
                                          .filter(account -> account.getAccountNumber() == fromAccountNumber)
                                          .findFirst()
                                          .orElseThrow(() -> new IllegalArgumentException("Source account not found or does not belong to user"));

        Account transferAccount = (transactionType.equalsIgnoreCase("TRANSFER"))
                ? accountRepo.findByAccountNumber(transferAccountNumber)
                    .orElseThrow(() -> new IllegalArgumentException("Transfer account not found"))
                : null;
        if (transferAccount != null && fromAccount.getAccountNumber() == transferAccount.getAccountNumber()) {
            throw new IllegalArgumentException("Cannot transfer money to the same account");
        }
        
        String message = String.format("Transaction Type: %s\nAmount: %.2f\nFrom Account: %d\n", transactionType, amount, fromAccountNumber);
        String subject = "Transaction Alert";
        if (transactionType.equalsIgnoreCase("TRANSFER")) {
            message += String.format("To Account: %d\n", transferAccountNumber);
        }

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
        
        String recipientEmail = getEmailByUsername(username);
        emailNotification.sendNotification(recipientEmail, message, subject);
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
        debitTransaction.setTransferAccountNumber(toAccount.getAccountNumber());
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
    public List<TransactionDto> viewPassbook(long accountNumber) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String firstName = authentication.getName();

        List<Account> userAccounts = accountRepo.findByCustomer_FirstName(firstName);

        boolean hasAccess = userAccounts.stream()
                                        .anyMatch(account -> account.getAccountNumber() == accountNumber);

        if (!hasAccess) {
            throw new AccessDeniedException("You do not have access to this account.");
        }

        List<Transaction> transactions = transactionRepo.findByAccount_AccountNumber(accountNumber);

        List<TransactionDto> transactionDtos = transactions.stream()
                                                          .map(this::toTransactionDtoMapper)
                                                          .collect(Collectors.toList());

        // Trigger the batch job with accountNumber parameter
        try {
            JobParameters jobParameters = new JobParametersBuilder()
                .addLong("accountNumber", accountNumber)
                .addString("timestamp", Instant.now().toString())
                .toJobParameters();
            JobExecution jobExecution = jobLauncher.run(exportJob, jobParameters);
            logger.info("Batch job completed with status: {}", jobExecution.getStatus());
        } catch (JobExecutionException e) {
            logger.error("Failed to execute batch job for accountNumber: {}", accountNumber, e);
            throw new RuntimeException("Failed to execute batch job", e);
        }

        return transactionDtos;
    }

    @Override
    @Transactional
    public void sendPassbookToUser(long accountNumber) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String firstName = authentication.getName();
        
        List<Account> userAccounts = accountRepo.findByCustomer_FirstName(firstName);
        
        boolean hasAccess = userAccounts.stream()
                                        .anyMatch(account -> account.getAccountNumber() == accountNumber);

        if (!hasAccess) {
            throw new AccessDeniedException("You do not have access to this account.");
        }

        List<Transaction> transactions = transactionRepo.findByAccount_AccountNumber(accountNumber);
        List<TransactionDto> transactionDtos = transactions.stream()
                                                          .map(this::toTransactionDtoMapper)
                                                          .collect(Collectors.toList());

        // Generate PDF
        byte[] pdfBytes = null;
        
            try {
				pdfBytes = PdfGenerator.generatePassbookPdf(transactionDtos);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (DocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        

        // Send Email
        String recipientEmail = getEmailByUsername(firstName);
        emailNotification.sendNotificationWithAttachment(recipientEmail, "Your Passbook is attached.", "Passbook Report", pdfBytes, "passbook.pdf");
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
        TransactionDto transactionDto = new TransactionDto();
        transactionDto.setTransactionId(transaction.getTransactionId()); 
        transactionDto.setTransactionType(transaction.getTransactionType().toString());
        transactionDto.setAmount(transaction.getAmount());
        transactionDto.setAccountNumber(transaction.getAccount().getAccountNumber());
        transactionDto.setTransferAccountNumber(transaction.getTransferAccountNumber());       
        transactionDto.setDate(transaction.getDate());
        return transactionDto;
    }
}
