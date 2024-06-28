package com.cms.services;

import java.util.List;

import com.cms.dto.UserDto;


public interface UserService {

	public UserDto saveUser(UserDto userDto);
	
	public Boolean existUser(String user);
	
	List<UserDto> getAllUser();


}
