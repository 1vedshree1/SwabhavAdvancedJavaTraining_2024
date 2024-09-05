package com.techlabs.bank.security;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.techlabs.bank.entity.Users;
import com.techlabs.bank.repository.UsersRepository;

@Service
public class CustomerUserDetailsService implements UserDetailsService{

	@Autowired
	UsersRepository userRepo;
	@Override 
	 public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException { 
	  Users user = userRepo.findByUserName(username) 
	    .orElseThrow(() -> new UsernameNotFoundException("User not found")); 
	 
	  Set<GrantedAuthority> authorities = user.getRoles().stream() 
	    .map(role -> new SimpleGrantedAuthority(role.getRoleName())).collect(Collectors.toSet()); 
	 
	  return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(), 
	    authorities); 
	 }

}
