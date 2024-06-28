package com.cms.dto;

import java.util.HashSet;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDto {

	private Integer id;
	
	private String name;

	private String email;

	private String password;
	
	private Set<RoleDto> roles=new HashSet<>();


}
