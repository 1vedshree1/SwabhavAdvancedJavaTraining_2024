package com.techlabs.company.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.techlabs.company.dto.PageResponseDto;
import com.techlabs.company.entity.Account;
import com.techlabs.company.entity.Employee;
import com.techlabs.company.repository.EmployeeRepository;

import jakarta.transaction.Transactional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepo;

    @Override
    public PageResponseDto<Employee> getAllEmployees(int pageNumber, int pageSize) {
       

        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Employee> employeePage = employeeRepo.findAll(pageable);
        
        // Create and populate the PageResponseDto
        PageResponseDto<Employee> employeePageDto = new PageResponseDto<Employee>();
        employeePageDto.setTotalPages(employeePage.getTotalPages());
        employeePageDto.setTotalElements(employeePage.getTotalElements());
        employeePageDto.setContent(employeePage.getContent());
        employeePageDto.setIslastPage(employeePage.isLast());

        return employeePageDto;
    }

    @Override
    public Optional<Employee> getEmployeeById(int employeeId) {
        return employeeRepo.findById(employeeId);
    }

    @Override
    public void addEmployees(Employee employee) {
        employeeRepo.save(employee);
    }

    @Transactional
    @Override
    public Account updateEmployeeAccount(int employeeId, Account account) {
        Optional<Employee> optionalEmployee = employeeRepo.findById(employeeId);
        if (!optionalEmployee.isPresent()) {
            return null; // Employee not found
        }

        Employee employeedb = optionalEmployee.get();
        Account accountdb = employeedb.getAccount();

        if (accountdb == null) {
            return null; // Handle case where the employee does not have an account
        }

        // Update account details
        accountdb.setAccountNumber(account.getAccountNumber());
        accountdb.setAccountHolderName(account.getAccountHolderName());

        // Save the updated employee (and account due to cascade type)
        employeedb = employeeRepo.save(employeedb);

        return employeedb.getAccount(); // Return the updated account
    }

    @Override
    public Optional<Account> getEmployeeAccount(int employeeId) {
        Optional<Employee> employeedb = employeeRepo.findById(employeeId);
        return employeedb.map(Employee::getAccount);
    }
}
