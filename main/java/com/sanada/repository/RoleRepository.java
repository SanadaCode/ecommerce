package com.sanada.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sanada.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {
	
	 Role findByCod(String cod);

}
