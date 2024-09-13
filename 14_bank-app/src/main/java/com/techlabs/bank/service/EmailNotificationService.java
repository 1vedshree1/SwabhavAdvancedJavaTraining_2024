package com.techlabs.bank.service;

public interface EmailNotificationService {
	

	void sendNotification(String recipient, String message, String subject);

}
