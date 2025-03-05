package com.usmantech.taskifyBackend.email;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@RestController	
@RequestMapping("/api")
public class EmailController {
	@Autowired
	JavaMailSender mailSender;
	
	@Value("${spring.mail.username}")
	private String fromMail;
	
    @GetMapping("/send-mail")
	public String sendMail() {
		try {
		SimpleMailMessage message=new SimpleMailMessage();
		message.setFrom("mohammedusmansha@gmail.com");
		message.setTo("usmanpersonal0@gmail.com");
		message.setSubject("This is sample test emial from mohammed");
		message.setText("Hi This is sample test body for test the first emial");
		mailSender.send(message);
		return "success";
		}
		
		catch (Exception e) {
			return e.getMessage();
		}
		
	}
    
    @GetMapping("/send-mail-msg")
    public String sendMailMsg() {
    	try {
    	SimpleMailMessage message =new SimpleMailMessage();//used for simple text mail
    	
    	message.setFrom(fromMail);//from mail address
    	message.setTo("mohammedusmanshaa@gmail.com");//to mail address
    	message.setSubject("It is 2nd sample text message");//subject for mail
    	message.setCc("usmanpersonal0@gmail.com");//cc mail address
    	message.setText("So this message find u well and good the main reson for this to achive the purpose");
    	//body for mail
    	mailSender.send(message);//send the mail using JavaMailSender class 
    	return "Message sent successfully";
    	}
    	catch (Exception e) {
return e.getMessage();		}
    	
    }
    @GetMapping("/send-mail-with-attachment")
    public void sendMailWithAttachment() {
    	try {	
    		MimeMessage message=mailSender.createMimeMessage();//used while sending any attachment
			MimeMessageHelper helper=new MimeMessageHelper(message, true);//takes two arguments 
			
			helper.setFrom(fromMail);
			helper.setTo("usmanpersonal0@gmail.com");
			helper.setSubject("This is a sample mail with attachment");
			helper.setText("hi there /n this msg finds u well and u can find attachment from the mohammed");
			helper.addAttachment("Madina.jpg", new File("C:\\Users\\admin\\Downloads\\Madina.jpg"));
			helper.addAttachment("Usman'new Resume.pdf", new File("C:\\Users\\admin\\Downloads\\Usman's new Resume.pdf"));
			
			mailSender.send(message);
			
    	} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
    @GetMapping("/send-html-emails")
    public String sendHTMLEmail() {
    	try {MimeMessage message= mailSender.createMimeMessage();
    	MimeMessageHelper helper=new MimeMessageHelper(message,true);
    	
		helper.setFrom(fromMail);		
    	helper.setTo("usmanpersonal0@gmail.com");
    	helper.setSubject("This is sample html email");
    	try (var inputStream=Objects.requireNonNull(EmailController.class.getResourceAsStream("/templates/index.html"))){
        	helper.setText(
        			new String(inputStream.readAllBytes(),StandardCharsets.UTF_8),true);
    	}
    	catch (Exception e) {
    		System.out.println(e.getMessage());
		}
    	helper.addInline("logo-black_.jpg", new File("C:\\Users\\admin\\Downloads\\logo-black_.jpg"));
    	mailSender.send(message);
    	return "Successfully send the mail";
    	} catch (MessagingException e) {
return e.getMessage();	}
		
	}
    @GetMapping("/send-html-email")
    public String mailWithHTML() {
    	try {
    		MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper helper= new MimeMessageHelper(message, true);
			helper.setFrom(fromMail);
	    	helper.setTo("usmanpersonal0@gmail.com");
	    	helper.setSubject("Testing sample HTML templet in mail");
	    	try(var inputStream=Objects.requireNonNull (EmailController.class.getResourceAsStream("/templates/index.html"))){
		    	helper.setText(new String (inputStream.readAllBytes(),StandardCharsets.UTF_8), true);
	    	}
	    	catch (Exception e) {
System.out.println(e.getMessage());			}
	    	
	    	helper.addInline("logo-black_.jpg", new File("C:\\Users\\admin\\Downloads\\logo-black_.jpg"));
	    	mailSender.send(message);

	    	return "Successfully send the mail";
	    	
    	} catch (MessagingException e) {
return e.getMessage();	}
    	
		
	}
	

}
