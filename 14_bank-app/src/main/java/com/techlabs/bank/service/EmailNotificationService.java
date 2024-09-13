package com.techlabs.bank.service;

public interface EmailNotificationService {
	

	void sendNotification(String recipient, String message, String subject);
	 void sendNotificationWithAttachment(String recipient, String message, String subject, byte[] attachment, String attachmentName);

}
