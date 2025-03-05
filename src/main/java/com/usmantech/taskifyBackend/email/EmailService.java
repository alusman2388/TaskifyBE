package com.usmantech.taskifyBackend.email;

import org.apache.tomcat.util.http.MimeHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {
	
	@Autowired
	private JavaMailSender mailSender;
	
	public void emailSender() {
		try {
		MimeMessage message= mailSender.createMimeMessage();		
		MimeMessageHelper helper=new MimeMessageHelper(message,true);
		helper.setFrom("mohammedusmansha@gmail.com");
		helper.setTo("usmanpersonal0@gmail.com");
		helper.setSubject("Testing the mail secheduler");
		helper.setText("Hi this is Mohammed usman pash this is just a sample testing mail.");
		mailSender.send(message);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
