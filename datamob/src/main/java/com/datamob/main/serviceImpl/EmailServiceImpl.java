package com.datamob.main.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;




@Service("emailService")
public class EmailServiceImpl {

	  private JavaMailSender mailSender;

	
	  @Autowired
	  public void EmailService(JavaMailSender mailSender) {
	    this.mailSender = mailSender;
	  }
	
	  
	  @Async
	public void sendEmail(SimpleMailMessage mail) {
	    mailSender.send(mail);
		
	}

}
