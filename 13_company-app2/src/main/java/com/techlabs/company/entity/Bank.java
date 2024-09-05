package com.techlabs.company.entity;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name="banks")
@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class Bank {
	@Column(name="bankId")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int bankId;
	@Column(name="bankName")
	private String bankName;
	@Column(name="branch")
	private String branch;
	@Column(name="ifscCode")
	private String ifscCode;
	@OneToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REFRESH,CascadeType.DETACH})
	List<Account> account;
	
	
	
	
	

}
