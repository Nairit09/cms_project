package com.cms.services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.cms.entities.OTPGenerator;

@Service
public class OTPservice {

	public String generateOTP() {
		return OTPGenerator.generateOTP();
	}

	private Map<String, String> otpStorage = new HashMap<>();

	public void storeOTP(String email, String otp) {
		otpStorage.put(email, otp);
	}

	public boolean validateOTP(String email, String otp) {

		String storedOTP = otpStorage.get(email);
		return storedOTP != null && storedOTP.equals(otp);
	}
}
