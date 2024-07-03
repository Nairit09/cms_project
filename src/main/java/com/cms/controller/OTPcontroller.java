package com.cms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cms.entities.OTPGenerator;
import com.cms.services.EmailService;
import com.cms.services.OTPservice;


@RestController
@RequestMapping("/api/otp")
public class OTPcontroller {

	@Autowired
	private OTPservice otpService;

	@Autowired
	private EmailService emailService;

	@GetMapping("/generate")
	public String generateOTP() {
		String otp = otpService.generateOTP();
		return "Generated OTP: " + otp;
	}

	@PostMapping("/sendOTP")
	public ResponseEntity<String> sendOTP(@RequestParam String email) {
		String otp = OTPGenerator.generateOTP();
		emailService.sendOTP(email, otp);
		otpService.storeOTP(email, otp); // Store OTP for validation
		return ResponseEntity.ok("OTP sent successfully to " + email);
	}

	@PostMapping("/validateOTP")
	public ResponseEntity<String> validateOTP(@RequestParam String email, @RequestParam String otp) {
		if (otpService.validateOTP(email, otp)) {
			return ResponseEntity.ok("OTP validated successfully");
		} else {
			return ResponseEntity.badRequest().body("Invalid OTP");
		}
	}
}
