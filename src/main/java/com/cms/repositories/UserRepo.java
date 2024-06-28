package com.cms.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cms.entities.User;

public interface UserRepo extends JpaRepository<User, Integer>{

	Boolean existsByName(String user);

	public Boolean existsByIdAndName(Integer id,String name);
}
