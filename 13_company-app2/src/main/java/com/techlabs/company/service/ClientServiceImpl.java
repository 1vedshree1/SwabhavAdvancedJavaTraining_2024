package com.techlabs.company.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techlabs.company.dto.ClientDto;

import com.techlabs.company.entity.Client;
import com.techlabs.company.entity.Employee;
import com.techlabs.company.repository.ClientRepository;
import com.techlabs.company.repository.EmployeeRepository;


@Service
public class ClientServiceImpl implements ClientService{
	@Autowired
	ClientRepository clientRepo;
	@Autowired
	EmployeeRepository employeeRepo;

	@Override
	public ClientDto addClient(ClientDto clientDto) {
		Client client = toClientMapper(clientDto);
		
		client = clientRepo.save(client);
		return toClientDtoMapper(client);
	}

	@Override
	public ClientDto getClientById(int clientId) {
		Optional<Client> optionalClient = clientRepo.findById(clientId);
		if(!optionalClient.isPresent())
			return null;
		Client dbclient = optionalClient.get();
		
		return toClientDtoMapper(dbclient);
	}

	@Override
	public List<ClientDto> getAllClients() {
		List<Client> clientList= clientRepo.findAll();
		List<ClientDto> clientDtoList= new ArrayList<ClientDto>();
		
		ClientDto clientDto = new ClientDto();
		
		for(Client client:clientList) {
			clientDto = toClientDtoMapper(client);
			clientDtoList.add(clientDto);
		}
		return clientDtoList;
		
		
		
	}
	
	@Override
	public Client allocateEmployees(int clientId, List<Integer> employeeIds) {
		
		Client dbClient = toClientMapper(getClientById(clientId));
		dbClient.setClientId(clientId);
		List<Employee> dbEmployees= dbClient.getEmployees();
		
		if(dbEmployees == null)
			dbEmployees = new ArrayList<>();
		
		List<Employee> employeesToadd= new ArrayList<>();
		
		employeeIds.forEach((employeeId)->{
			Optional<Employee> optionalEmployee = employeeRepo.findById(employeeId);
			if(!optionalEmployee.isPresent())
				throw new NullPointerException("employee does not exist.");
			
			Employee employee = optionalEmployee.get();
			employee.setClient(dbClient);
			employeesToadd.add(employee);
			});
		
		dbEmployees.addAll(employeesToadd);
		dbClient.setEmployees(dbEmployees);
		
		
		// TODO Auto-generated method stub
		return clientRepo.save(dbClient);
	}
	
	public Client toClientMapper(ClientDto clientDto) {
		
		Client client = new Client();
		
		client.setClientId(clientDto.getClientId());
		client.setCompanyName(clientDto.getCompanyName());
		client.setAddress(clientDto.getAddress());
		client.setRegistrationNumber(clientDto.getRegistrationNumber());
		client.setContactPerson(clientDto.getContactPerson());
		client.setContactNumber(clientDto.getContactNumber());
		client.setContactEmail(clientDto.getContactEmail());
		client.setCreationDate(clientDto.getCreationDate());
		client.setKycStatus(clientDto.getKycStatus());
		client.setStatus(clientDto.getStatus());
		return client;
		
	}
	public ClientDto toClientDtoMapper(Client client) {
		
		ClientDto clientDto = new ClientDto();
		
		clientDto.setClientId(client.getClientId());
		clientDto.setCompanyName(client.getCompanyName());
		clientDto.setAddress(client.getAddress());
		clientDto.setRegistrationNumber(client.getRegistrationNumber());
		clientDto.setContactPerson(client.getContactPerson());
		clientDto.setContactNumber(client.getContactNumber());
		clientDto.setContactEmail(client.getContactEmail());
		clientDto.setCreationDate(client.getCreationDate());
		clientDto.setKycStatus(client.getKycStatus());
		clientDto.setStatus(client.getStatus());
		
		return clientDto;
		
	}

}
