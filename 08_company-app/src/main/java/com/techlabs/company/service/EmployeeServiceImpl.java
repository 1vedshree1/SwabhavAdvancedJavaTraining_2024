package com.techlabs.company.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.techlabs.company.entity.Employee;
import com.techlabs.company.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService{
	@Autowired
	private EmployeeRepository employeeRepo;

	@Override
	public Page<Employee> getAllEmployees(int pageSize, int pageNumber) {
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		return employeeRepo.findAll(pageable);
	}

	@Override
	public Optional<Employee> getEmployeeById(int employeeId) {
		
		return employeeRepo.findById(employeeId);
	}

	@Override
	public void addEmployee(Employee employee) {
		employeeRepo.save(employee);
		
	}

	@Override
	public Page<Employee> getEmployeeByName(String firstName, int pageNumber, int pageSize) {
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		return employeeRepo.findByFirstName(firstName, pageable);
	}

	@Override
	public void deleteEmployeeById(int employeeId) {
		employeeRepo.deleteById(employeeId);
		
	}
	

}
