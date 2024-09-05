package com.techlabs.email.service;

import com.techlabs.email.entity.EmailDetails;

public interface EmailService {
	
	 String sendSimpleMail(EmailDetails details);
	 
	 String sendMailWithAttachment(EmailDetails details);

}
