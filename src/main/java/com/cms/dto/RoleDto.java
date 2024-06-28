package com.cms.dto;

import java.util.List;

import com.cms.entities.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoleDto {

	private Integer id;

	private String name;

	private String code;

	//private List<UserDto> users;
}
