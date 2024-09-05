package com.techlabs.bank.entity;

import java.util.List;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name="users")
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class User {
	
	@Column(name="userId")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userId;
	@Column(name="userName")
	private String userName;
	@Column(name="userPassword")
	private String userPassword;
	@Column(name="userType")
	private UserType userType;
	
//	@Column(name="userId")
//	 @Id
//	 @GeneratedValue(strategy = GenerationType.IDENTITY)
//	private int userId;
//	@Column(name="userName")
//	private String userName;
//	@Column(name="password")
//	private String password;
//	
//	@ManyToMany
//	@JoinTable(name="user-role",
//	joinColumns = @JoinColumn(name="userId"),
//	inverseJoinColumns = @JoinColumn(name="roleId"))
//	List<Role> roles;

}
