package com.techlabs.security.security;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.techlabs.security.entity.User;
import com.techlabs.security.repository.UserRepository;

@Service
public class CustomerUserDetailsService implements UserDetailsService{

	@Autowired
	UserRepository userRepo;
	@Override 
	 public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException { 
	  User user = userRepo.findByUserName(username) 
	    .orElseThrow(() -> new UsernameNotFoundException("User not found")); 
	 
	  Set<GrantedAuthority> authorities = user.getRoles().stream() 
	    .map(role -> new SimpleGrantedAuthority(role.getRoleName())).collect(Collectors.toSet()); 
	 
	  return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(), 
	    authorities); 
	 }

}
