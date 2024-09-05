package com.techlabs.bank.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@AllArgsConstructor
@Data
public class ProfileDto {
	
	@Pattern(regexp = "^[A-Za-z]+$", message = "First name should not contain digits")
	private String firstName;
	
	@Pattern(regexp = "^[A-Za-z]+$", message = "First name should not contain digits")
	private String lastName;
	
	@Email(message = "Email should be valid")
	private String email;
	private String password;
	
	@Size(min = 8, message = "Password should have at least 8 characters")
	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$", message = "Password must contain at least one uppercase letter, one lowercase letter, one digit, and one special character (@$!%*?&) and must be at least 8 characters long")
	private String newPassword;

}
