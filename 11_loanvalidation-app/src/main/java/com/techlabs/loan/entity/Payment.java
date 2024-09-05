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
@Table(name="payment")
@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class Payment {
	@Column(name="paymentid")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int paymentId;
	@Column(name="paymentdate")
    @NotNull(message = "Payment date is mandatory")
    @PastOrPresent(message = "Payment date must be today or in the past")
    private LocalDate paymentDate;

    @Column(name="amount")
    @Positive(message = "Amount must be positive")
    private double amount;

    @Column(name="paymentmode")
    @Enumerated(EnumType.STRING)
    @NotNull(message = "Payment mode is mandatory")
    private PaymentModeType paymentMode;

    @Column(name="paymentstatus")
    @Enumerated(EnumType.STRING)
    @NotNull(message = "Payment status is mandatory")
    private PaymentStatusType paymentStatus;

}
