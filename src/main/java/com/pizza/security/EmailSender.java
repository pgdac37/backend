package com.pizza.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailSender {
	@Autowired
	private JavaMailSender mailSender;
	
	public void sendEmail(String toEmail, String subject, String body) {
		SimpleMailMessage mail  = new SimpleMailMessage();
		
		mail.setFrom("pizzaproject2203@gmail.com");
		mail.setTo(toEmail);
		mail.setText(body);
		mail.setSubject(subject);
		
		mailSender.send(mail);
	}

}
