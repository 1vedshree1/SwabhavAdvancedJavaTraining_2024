package com.techlabs.bank.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.techlabs.bank.dto.CustomerDto;
import com.techlabs.bank.dto.LoginDto;
import com.techlabs.bank.dto.RegistrationDto;
import com.techlabs.bank.entity.Customer;
import com.techlabs.bank.entity.Role;
import com.techlabs.bank.entity.User;
import com.techlabs.bank.exception.UserApiException;
import com.techlabs.bank.repository.CustomerRepository;
import com.techlabs.bank.repository.RoleRepository;
import com.techlabs.bank.repository.UsersRepository;
import com.techlabs.bank.security.JwtTokenProvider;


import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {

	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private UsersRepository userRepo;
	@Autowired
	private RoleRepository RoleRepo;
	@Autowired
	private CustomerRepository customerRepo;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private JwtTokenProvider jwtTokenProvider;
	@Autowired
    private CaptchaService captchaService;
	
	private static final Logger logger = LoggerFactory.getLogger(AuthServiceImpl.class);
	
	@Override 
	 public User register(RegistrationDto registerDto, String captchaResponse) { 
		
		
		
		if (!captchaService.validateCaptcha(captchaResponse)) {
            throw new UserApiException(HttpStatus.BAD_REQUEST, "Captcha verification failed");
        }
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
	public Customer addCustomer(CustomerDto customerDto) { 
	     if (customerRepo.existsByFirstName(customerDto.getFirstName())) { 
	         throw new UserApiException(HttpStatus.BAD_REQUEST, "Customer already exists"); 
	     } 
	 
	     User user = new User(); 
	     user.setUserName(customerDto.getFirstName()); 
	     user.setPassword(passwordEncoder.encode(customerDto.getPassword())); 
	 
	     List<Role> roles = new ArrayList<>(); 
	 
	     Role userRole = RoleRepo.findByRoleName("ROLE_CUSTOMER").get(); 
	     roles.add(userRole); 
	     user.setRoles(roles); 
	 
	     userRepo.save(user); 
	     logger.info("username is created for this customer.");
	     Customer customer = new Customer();
	        customer.setFirstName(customerDto.getFirstName());
	        customer.setLastName(customerDto.getLastName());
	        customer.setEmail(customerDto.getEmail());
	        customer.setPassword(passwordEncoder.encode(customerDto.getPassword())); // Encrypt password
	        customer.setUser(user);

	        return customerRepo.save(customer);
	     
	     
	 }
	

	@Override
	 public String login(LoginDto loginDto, String captchaResponse) {
		
		
		
		if (!captchaService.validateCaptcha(captchaResponse)) {
            throw new UserApiException(HttpStatus.BAD_REQUEST, "Captcha verification failed");
        }
		
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
