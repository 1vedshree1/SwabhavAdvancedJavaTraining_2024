package com.techlabs.transaction.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techlabs.transaction.entity.Address;
import com.techlabs.transaction.repository.AddressRepository;

@Service
public class AddressServiceImpl implements AddressService{

	@Autowired
	private AddressRepository addressRepo; 
	@Override
	public List<Address> getAllAddresses() {
		// TODO Auto-generated method stub
		return addressRepo.findAll();
	}

	@Override
	public void addAddresses(Address address) {
		addressRepo.save(address);
		
	}

}
