package com.techlabs.company.service;

import java.util.List;

import com.techlabs.company.dto.ClientDto;
import com.techlabs.company.entity.Client;

public interface ClientService {
	
	ClientDto addClient(ClientDto clientDto);
	ClientDto getClientById(int clientId);
	List<ClientDto> getAllClients();
	Client allocateEmployees(int clientId, List<Integer> employeeIds);

}
