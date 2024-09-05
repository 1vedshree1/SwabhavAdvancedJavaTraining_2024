package com.techlabs.security.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.techlabs.security.dto.LoginDto;
import com.techlabs.security.dto.RegistrationDto;
import com.techlabs.security.entity.Role;
import com.techlabs.security.entity.User;
import com.techlabs.security.exception.UserApiException;
import com.techlabs.security.repository.RoleRepository;
import com.techlabs.security.repository.UserRepository;
import com.techlabs.security.security.JwtTokenProvider;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

@Service
@AllArgsConstructor
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private RoleRepository RoleRepo;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private JwtTokenProvider jwtTokenProvider;
	
	@Override 
	 public User register(RegistrationDto registerDto) { 
	     if (userRepo.existsByUserName(registerDto.getUserName())) { 
	         throw new UserApiException(HttpStatus.BAD_REQUEST, "User already exists"); 
	     } 
	 
	     User user = new User(); 
	     user.setUserName(registerDto.getUserName()); 
	     user.setPassword(passwordEncoder.encode(registerDto.getPassword())); 
	 
	     List<Role> roles = new ArrayList<>(); 
	 
	     Role userRole = RoleRepo.findByRoleName(registerDto.getRole()).get(); 
	     roles.add(userRole); 
	     user.setRoles(roles); 
	 
	     return userRepo.save(user); 
	 }

	@Override
	 public String login(LoginDto loginDto) {
	     try {
	         Authentication authentication = authenticationManager.authenticate(
	                 new UsernamePasswordAuthenticationToken(
	                         loginDto.getUserName(), loginDto.getPassword())
	         );
	         SecurityContextHolder.getContext().setAuthentication(authentication);
	         String token = jwtTokenProvider.GenerateToken(authentication);

	         return token;
	     } catch (BadCredentialsException e) {
	         throw new UserApiException(HttpStatus.NOT_FOUND, "Username or Password is incorrect");
	     }
	 }
	
	

}
