package com.cms.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.cms.entities.EmailRequest;
import com.cms.services.EmailService;

@Service
public class EmailServiceImpl implements EmailService {

	@Autowired
	private JavaMailSender  javaMailSender;

	@Override
	public void sendEmail(EmailRequest emailRequest) {

		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setFrom("abc@xyz");
		mailMessage.setTo(emailRequest.getRecipient());
		mailMessage.setText(emailRequest.getMsgBody());
		mailMessage.setSubject(emailRequest.getSubject());
		javaMailSender.send(mailMessage);

	}

	@Override
	public void sendOTP(String email, String otp) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(email);
		message.setSubject("Your OTP for Verification");
		message.setText("Your OTP is: " + otp);
		javaMailSender.send(message);
	}

}