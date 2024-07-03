package com.cms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cms.entities.EmailRequest;
import com.cms.services.EmailService;



@RestController
public class EmailController {

	@Autowired
	private EmailService emailService;

	@PostMapping("/mail")
	public void sendEMail(@RequestBody EmailRequest emailRequest) {
		System.out.println("Going to Send email: " + emailRequest.toString());
		emailService.sendEmail(emailRequest);
	}
}