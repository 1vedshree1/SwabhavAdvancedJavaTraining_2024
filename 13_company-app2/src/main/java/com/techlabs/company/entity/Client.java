package com.techlabs.company.entity;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Entity
@Table(name="clients")
@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Client {
	@Column(name="client_id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int clientId;
	@Column(name="comapny_name")
	private String companyName;
	@Column(name="registration_number")
	private int registrationNumber;
	@Column(name="contact_person")
	private String contactPerson;
	@Column(name="contact_email")
	private String contactEmail;
	@Column(name="contact_number")
	private long contactNumber;
	@Column(name="address")
	private String address;
	@Column(name="status")
	private StatusType status;
	@Column(name="creation_date")
	private Date creationDate;
	@Column(name="kycstatus")
	private KycStatusType kycStatus;
	@OneToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REFRESH,CascadeType.DETACH})
	@JsonIgnore
	List<Employee>employees;
	
	
	
	
	
	

}
