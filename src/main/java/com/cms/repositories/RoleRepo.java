package com.cms.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cms.entities.Role;

public interface RoleRepo extends JpaRepository<Role, Integer>{

	Role findByName(String roleName);

}
