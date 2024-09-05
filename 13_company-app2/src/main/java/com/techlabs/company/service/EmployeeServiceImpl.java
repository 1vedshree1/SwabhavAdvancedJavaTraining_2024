package com.techlabs.company.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.techlabs.company.dto.EmployeeDto;
import com.techlabs.company.dto.PageResponseDto;
import com.techlabs.company.entity.Account;
import com.techlabs.company.entity.Client;
import com.techlabs.company.entity.Employee;
import com.techlabs.company.repository.ClientRepository;
import com.techlabs.company.repository.EmployeeRepository;

import jakarta.transaction.Transactional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepo;
    @Autowired
    private ClientRepository clientRepo;

    @Override
    public PageResponseDto<Employee> getAllEmployees(int pageNumber, int pageSize) {
       

        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Employee> employeePage = employeeRepo.findAll(pageable);
        
        
        PageResponseDto<Employee> employeePageDto = new PageResponseDto<Employee>();
        employeePageDto.setTotalPages(employeePage.getTotalPages());
        employeePageDto.setTotalElements(employeePage.getTotalElements());
        employeePageDto.setContent(employeePage.getContent());
        employeePageDto.setIslastPage(employeePage.isLast());

        return employeePageDto;
    }

//    @Override
//    public Optional<Employee> getEmployeeById(int employeeId) {
//        return employeeRepo.findById(employeeId);
//    }

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
    
    

	@Override
	public EmployeeDto addEmployee(EmployeeDto employeeDto) {
		
		Employee employee = toEmployeeMapper(employeeDto);
		employee = employeeRepo.save(employee);
		
		
		return toEmployeeDtoMapper(employee);
	}

	@Override

	public List<EmployeeDto> getEmployees() {
		
		List<Employee> employeeList = employeeRepo.findAll();
		List<EmployeeDto> employeeDtoList = new ArrayList<EmployeeDto>();
		EmployeeDto employeeDto = new EmployeeDto();
		for(Employee employee:employeeList) {
			employeeDto= toEmployeeDtoMapper(employee);
			employeeDtoList.add(employeeDto);
			
		}
		return employeeDtoList;
		
	
	}
	
	

	@Override
	public EmployeeDto getEmployeeById(int employeeId) {
		
		Optional<Employee> optionalEmployee = employeeRepo.findById(employeeId);
		if(!optionalEmployee.isPresent())
			return null;
		Employee dbEmployee = optionalEmployee.get();
		return toEmployeeDtoMapper(dbEmployee);
	}
	
	@Override
	public Employee allocateInstructor(int employeeId, int clientId) {
        Optional<Employee> optionalEmployee = employeeRepo.findById(employeeId);
        if (!optionalEmployee.isPresent()) {
            throw new NullPointerException("employee does not exist.");
        }
        Employee dbEmployee = optionalEmployee.get();
        
        Optional<Client> optionalClient = clientRepo.findById(clientId);
        if (!optionalClient.isPresent()) {
            throw new NullPointerException("Client does not exist.");
        }
        Client client = optionalClient.get();
        
        dbEmployee.setClient(client);
        
        return employeeRepo.save(dbEmployee);
    }
	
	public EmployeeDto toEmployeeDtoMapper(Employee employee) {
		
		EmployeeDto employeeDto = new EmployeeDto();
		employeeDto.setEmployeeId(employee.getEmployeeId());
		employeeDto.setFirstName(employee.getFirstName());
		employeeDto.setLastName(employee.getLastName());
		employeeDto.setHireDate(employee.getHireDate());
		employeeDto.setPosition(employee.getPosition());
		employeeDto.setPhoneNumber(employee.getPhoneNumber());
		employeeDto.setEmail(employee.getEmail());
		employeeDto.setSalary(employee.getSalary());
		employeeDto.setStatus(employee.getStatus());
		
		return employeeDto;	
		
	}
	
	public Employee toEmployeeMapper(EmployeeDto employeeDto) {
		
		Employee employee = new Employee();
		employee.setFirstName(employeeDto.getFirstName());
		employee.setLastName(employeeDto.getLastName());
		employee.setHireDate(employeeDto.getHireDate());
		employee.setPosition(employeeDto.getPosition());
		employee.setPhoneNumber(employeeDto.getPhoneNumber());
		employee.setEmail(employeeDto.getEmail());
		employee.setSalary(employeeDto.getSalary());
		employee.setStatus(employeeDto.getStatus());
		return employee;
		
	}
	
	
}
