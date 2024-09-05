package com.techlabs.transaction.service;

import java.util.List;

import com.techlabs.transaction.entity.Address;

public interface AddressService {

	public List<Address> getAllAddresses();
	public void addAddresses(Address address);
}
