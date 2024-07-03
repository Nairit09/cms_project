package com.cms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cms.dto.ForgotPasswordRequestDto;
import com.cms.dto.ResetPasswordDto;
import com.cms.dto.UserDto;
import com.cms.services.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/cms/")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/create")
	public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
		UserDto createUser = userService.saveUser(userDto);
		return new ResponseEntity<>(createUser, HttpStatus.CREATED);
	}

	@GetMapping("/alluser")
	public ResponseEntity<?> getAllCollege() {
		List<UserDto> users = null;
		try {
			users = userService.getAllUser();
			if (CollectionUtils.isEmpty(users)) {
				return new ResponseEntity<>("Users not found ", HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			log.error("Error:{}", e.getMessage());
		}
		return new ResponseEntity<>(users, HttpStatus.OK);
	}
	   @PostMapping("/forgot-password")
	    public ResponseEntity<String> forgotPassword(@RequestBody ForgotPasswordRequestDto forgotPasswordRequestDto) {
	        userService.sendOtpForPasswordReset(forgotPasswordRequestDto.getEmail());
	        return ResponseEntity.ok("OTP sent to your email address.");
	    }

	    @PostMapping("/reset-password")
	    public ResponseEntity<String> resetPassword(@RequestBody ResetPasswordDto resetPasswordDto) {
	        userService.resetPassword(resetPasswordDto.getEmail(), resetPasswordDto.getOtp(), resetPasswordDto.getNewPassword());
	        return ResponseEntity.ok("Password reset successfully.");
	    }
	}

