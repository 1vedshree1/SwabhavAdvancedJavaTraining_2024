package com.techlabs.company.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class BankDto {

    private int bankId;

    @NotBlank(message = "Bank name cannot be blank")
    @Size(min = 1, max = 100, message = "Bank name must be between 1 and 100 characters")
    private String bankName;

    @NotBlank(message = "Branch cannot be blank")
    @Size(min = 1, max = 100, message = "Branch must be between 1 and 100 characters")
    private String branch;

    @NotBlank(message = "IFSC code cannot be blank")
    @Pattern(regexp = "^[A-Z]{4}0[A-Z0-9]{6}$", message = "Invalid IFSC code format")
    private String ifscCode;
}
