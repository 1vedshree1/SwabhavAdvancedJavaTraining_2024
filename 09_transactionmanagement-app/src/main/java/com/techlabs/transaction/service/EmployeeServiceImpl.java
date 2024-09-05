package com.techlabs.transaction.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional; // Import Transactional annotation
import com.techlabs.transaction.entity.Address;
import com.techlabs.transaction.entity.Employee;
import com.techlabs.transaction.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepo;

    @Autowired
    private AddressService addressService;

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepo.findAll();
    }

    @Override
    @Transactional
    public Employee addEmployees(Employee employee, String addressStr) {

        Employee employeeSavedToDB = employeeRepo.save(employee);

        Address address = new Address();
        address.setAddress(addressStr);
        address.setEmployee(employeeSavedToDB);

        try {
            addressService.addAddresses(address);
        } catch (Exception e) {
            employeeRepo.delete(employeeSavedToDB);
            throw new RuntimeException("Failed to save address. Rolled back employee save.", e);
        }

        return employeeSavedToDB;
    }
}
