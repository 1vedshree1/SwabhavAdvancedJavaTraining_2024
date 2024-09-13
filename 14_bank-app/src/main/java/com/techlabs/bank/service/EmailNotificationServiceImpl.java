package com.techlabs.bank.service;


import java.io.ByteArrayInputStream;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.util.ByteArrayDataSource;

@Service
public class EmailNotificationServiceImpl implements EmailNotificationService {

    @Autowired
    private JavaMailSender mailSender;

  

	@Override
	public void sendNotification(String recipient,String message,String subject) {
		
		SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(recipient);
        mailMessage.setSubject(subject);
        mailMessage.setText(message);

        mailSender.send(mailMessage);
		
	}
	
	@Override
    public void sendNotificationWithAttachment(String recipient, String message, String subject, byte[] attachment, String attachmentName) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true);
            messageHelper.setTo(recipient);
            messageHelper.setSubject(subject);
            messageHelper.setText(message);

            // Attach the PDF
            ByteArrayInputStream attachmentStream = new ByteArrayInputStream(attachment);
            try {
				messageHelper.addAttachment(attachmentName, new ByteArrayDataSource(attachmentStream, "application/pdf"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }
    }

}

