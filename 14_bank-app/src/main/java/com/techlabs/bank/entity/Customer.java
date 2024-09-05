package com.techlabs.bank.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name="customers")
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Customer {
	@Column(name="customerId")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int customerId;
	@Column(name="firstName")
	@Pattern(regexp = "^[A-Za-z]+$", message = "First name should not contain digits")
	private String firstName;
	@Column(name="lastName")
	@Pattern(regexp = "^[A-Za-z]+$", message = "Last name should not contain digits")
	private String lastName;
	@Column(name="email")
	 @Email(message = "Email should be valid")
	private String email;
	@Column(name="password")
	@Size(min = 8, message = "Password should have at least 8 characters")
    
	private String password;
	@OneToMany(mappedBy = "customer",cascade=CascadeType.ALL)
	List<Account> accounts;
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="userId")
	private Users user;

	
}
