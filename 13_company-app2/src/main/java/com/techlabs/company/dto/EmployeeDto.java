package com.techlabs.company.dto;

import java.time.LocalDate;

import com.techlabs.company.entity.StatusType;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class EmployeeDto {

    @Positive(message = "Employee ID must be a positive number")
    private int employeeId;

    @NotBlank(message = "First name cannot be blank")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "First name must contain only letters")
    private String firstName;

    @NotBlank(message = "Last name cannot be blank")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Last name must contain only letters")
    private String lastName;

    @NotNull(message = "Phone number cannot be null")
    @Positive(message = "Phone number must be a positive number")
    @Pattern(regexp = "^\\d{10}$", message = "Phone number must be a 10-digit number")
    private String phoneNumber;

    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Position cannot be blank")
    private String position;

    @NotNull(message = "Hire date cannot be null")
    private LocalDate hireDate;

    @Positive(message = "Salary must be a positive number")
    private double salary;

    @NotNull(message = "Status cannot be null")
    private StatusType status;
}
