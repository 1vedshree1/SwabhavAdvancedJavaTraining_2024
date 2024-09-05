package com.techlabs.company.dto;

import java.util.Date;

import com.techlabs.company.entity.KycStatusType;
import com.techlabs.company.entity.StatusType;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class ClientDto {

   
    private int clientId;

    @NotBlank(message = "Company name cannot be blank")
    @Size(min = 1, max = 100, message = "Company name must be between 1 and 100 characters")
    private String companyName;

    @Positive(message = "Registration number must be a positive number")
    private int registrationNumber;

    @NotBlank(message = "Contact person cannot be blank")
    @Size(min = 1, max = 100, message = "Contact person must be between 1 and 100 characters")
    private String contactPerson;

    @NotBlank(message = "Contact email cannot be blank")
    @Email(message = "Invalid email format")
    private String contactEmail;

   
    private long contactNumber;

    @NotBlank(message = "Address cannot be blank")
    @Size(min = 1, max = 255, message = "Address must be between 1 and 255 characters")
    private String address;

    @NotNull(message = "Status cannot be null")
    private StatusType status;

    @NotNull(message = "Creation date cannot be null")
    private Date creationDate;

    @NotNull(message = "KYC status cannot be null")
    private KycStatusType kycStatus;
}
