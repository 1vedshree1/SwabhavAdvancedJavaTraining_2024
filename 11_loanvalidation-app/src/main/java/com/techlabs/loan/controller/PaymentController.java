package com.techlabs.loan.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.techlabs.loan.dto.PageResponseDto;

import com.techlabs.loan.entity.Payment;
import com.techlabs.loan.service.PaymentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/loanapp")
public class PaymentController {
	@Autowired
	private PaymentService paymentservice;
	@GetMapping("/payments")
	public ResponseEntity<PageResponseDto<Payment>> getAllPayments(@RequestParam int pageNumber, @RequestParam int pageSize) {
		
		return ResponseEntity.ok(paymentservice.getAllPayments(pageNumber,pageSize));
	}
	
	@GetMapping("/payments/{paymentId}")
	public ResponseEntity<Optional<Payment>> getAllPayments(@PathVariable int paymentId){
		return ResponseEntity.ok(paymentservice.getpayment(paymentId));
		
	}
	
	@PostMapping("/payments")
	public String addPayment(@Valid @RequestBody Payment payment) {
		paymentservice.addPayment(payment);
		return "payment added successfully";
	}
	
	@PutMapping("/payments/{paymentId}")
	public String updatePayment(@PathVariable int paymentId, @RequestBody Payment payment) {
		paymentservice.updatePaymentById(paymentId, payment);
		return "payment updated successfully";
		
	}


}
