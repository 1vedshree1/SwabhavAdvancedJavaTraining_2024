package com.techlabs.loan.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techlabs.loan.entity.Payment;
import com.techlabs.loan.service.PaymentService;

@RestController
@RequestMapping("/loanapp")
public class PaymentController {
	
	@Autowired
	PaymentService paymentService;
	
	@GetMapping("/payments")
	public ResponseEntity<List<Payment>> getAllPayments(){
		return ResponseEntity.ok(paymentService.getAllPayments());
		
	}
	@GetMapping("/payments/{paymentId}")
	public ResponseEntity<Payment> getAllPayments(@PathVariable int paymentId){
		return ResponseEntity.ok(paymentService.getpayment(paymentId));
		
	}
	
	@PostMapping("/payments")
	public String addPayment(@RequestBody Payment payment) {
		paymentService.addPayment(payment);
		return "payment added successfully";
	}
	
	@PutMapping("/payments/{paymentId}")
	public String updatePayment(@PathVariable int paymentId, @RequestBody Payment payment) {
		paymentService.updatePaymentById(paymentId, payment);
		return "payment updated successfully";
		
	}

}
