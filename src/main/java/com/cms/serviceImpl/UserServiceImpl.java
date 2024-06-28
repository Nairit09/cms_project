package com.cms.serviceImpl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import com.cms.dto.UserDto;

import com.cms.entities.User;
import com.cms.exception.ResourceNotFoundException;
import com.cms.repositories.RoleRepo;
import com.cms.repositories.UserRepo;
import com.cms.services.UserService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private RoleRepo roleRepo;

//	@Override
//
//	public UserDto saveUserWithRole(UserDto userDto) {
//		User saveUser = null;
//		
//		try {
//			User user = mapper.map(userDto, User.class);
//
//			saveUser = userRepo.save(user);
//			if (ObjectUtils.isEmpty(saveUser)) {
//				return null;
//			}
//
//		} catch (Exception e) {
//			log.error("Error:{}", e.getMessage());
//		}
//		UserDto map = mapper.map(saveUser, UserDto.class);
//		return map;
//
//	}
	public UserDto saveUser(UserDto userDto) {

		User user = mapper.map(userDto, User.class);

		user.setName(user.getName().trim());
		UserDto saveUserDto = null;

//		setUserDetails(user);

		if (ObjectUtils.isEmpty(user.getId())) {

			Boolean existUserName = existUser(user.getName().trim());
//			if (existUserName) {
//				throw new ExistResourceException("User Allready exist");
//			}

			User saveUser = userRepo.save(user);

			saveUserDto = mapper.map(saveUser, UserDto.class);
		} else {
			User existUser = userRepo.findById(user.getId()).orElse(null);

			if (existUser == null) {
//				throw new ResourceNotFoundException("user not found with id = " + user.getId());
			}

			Boolean existIdAndName = existIdAndUser(user.getId(), user.getName());
			if (!existIdAndName) {
				Boolean existUserName = existUser(user.getName().trim());
				if (existUserName) {
//					throw new ExistResourceException("user already exist");
				}
			}

			if (StringUtils.hasLength(user.getName())) {
				existUser.setName(user.getName());
			}

			if (StringUtils.hasLength(user.getEmail())) {
				existUser.setEmail(user.getEmail());
			}

			User updateUser = userRepo.save(existUser);
			saveUserDto = mapper.map(updateUser, UserDto.class);
		}
		return saveUserDto;
	}

	private Boolean existIdAndUser(Integer id, String name) {
		return userRepo.existsByIdAndName(id, name);
	}

	@Override
	public Boolean existUser(String user) {
		return userRepo.existsByName(user);
	}

	@Override
	public List<UserDto> getAllUser() {
		List<UserDto> userDto = null;

		try {

			List<User> allUser = userRepo.findAll();

			userDto = allUser.stream().map(user -> mapper.map(user, UserDto.class)).collect(Collectors.toList());
			if (CollectionUtils.isEmpty(allUser)) {
				return null;
			}
		} catch (Exception e) {
			log.error("Error:{}", e.getMessage());
		}
		return userDto;
	}

}
