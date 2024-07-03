package com.cms.services;

import com.cms.entities.EmailRequest;

public interface EmailService {

	public void sendEmail(EmailRequest emailRequest);

	public void sendOTP(String email, String otp);
}
