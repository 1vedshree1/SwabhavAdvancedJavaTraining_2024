package com.techlabs.loan.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name="loan")
@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class Loan {
	@Column(name="loanid")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int loanId;
	 @Column(name="interestrate")
	    @DecimalMin(value = "0.0", inclusive = true, message = "Interest rate must be at least 0%")
	    @DecimalMax(value = "100.0", inclusive = true, message = "Interest rate cannot exceed 100%")
	    private float interestRate;

	    @Column(name="loanterm")
	    @Positive(message = "Loan term must be positive")
	    private int loanTerm;

	    @Column(name="startdate")
	    @NotNull(message = "Start date is mandatory")
	    @PastOrPresent(message = "Start date must be today or in the past")
	    private LocalDate startDate;

	    @Column(name="enddate")
	    @NotNull(message = "End date is mandatory")
	    @FutureOrPresent(message = "End date must be today or in the future")
	    private LocalDate endDate;

	    @Column(name="loanstatus")
	    @Enumerated(EnumType.STRING)
	    @NotNull(message = "Loan status is mandatory")
	    private LoanStatusType loanStatus;

}
