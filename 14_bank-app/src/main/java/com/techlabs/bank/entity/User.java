package com.techlabs.bank.entity;

import java.util.List;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name="user")
@RequiredArgsConstructor
@AllArgsConstructor
@Data
public class User {
	@Column(name="userId")
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userId;
	@Column(name="userName")
	private String userName;
	@Column(name="password")
	private String password;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name="user-role",
	joinColumns = @JoinColumn(name="userId"),
	inverseJoinColumns = @JoinColumn(name="roleId"))
	List<Role> roles;

}
