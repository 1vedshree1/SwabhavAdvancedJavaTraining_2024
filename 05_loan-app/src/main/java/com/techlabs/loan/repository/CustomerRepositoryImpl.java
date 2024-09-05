package com.techlabs.loan.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.techlabs.loan.entity.Customer;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
public class CustomerRepositoryImpl implements CustomerRepository{
	
	@Autowired
	EntityManager manager;

	@Override
	public List<Customer> getAllCustomers() {
		TypedQuery<Customer> query = manager.createQuery("select c from Customer c", Customer.class);
		
		return query.getResultList();
	}

	@Override
	public Customer getCustomer(int customerId) {
		
		return manager.find(Customer.class, customerId);
	}
	
	@Transactional
	@Override
	public void addCustomer(Customer customer) {
		manager.persist(customer);
		
		
	}

	@Transactional
	@Override
	public void updateCustomerById(int customerId, Customer customer) {
		
		Customer excustomer =  manager.find(Customer.class, customerId);
		
		excustomer.setFirstName(customer.getFirstName());
		excustomer.setLastName(customer.getLastName());
		excustomer.setDateOfBirth(customer.getDateOfBirth());
		excustomer.setMobileNumber(customer.getMobileNumber());
		
		manager.merge(excustomer);
		
	}

}
