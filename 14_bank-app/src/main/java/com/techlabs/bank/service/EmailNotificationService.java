package com.techlabs.bank.service;

public interface EmailNotificationService {
	 void sendTransactionNotification(String recipient,String message);

}
