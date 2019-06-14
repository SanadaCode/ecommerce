package com.sanada.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sanada.entity.Role;
import com.sanada.repository.RoleRepository;

@Service
public class RoleService {
	
	@Autowired
	private RoleRepository roleRepository;
	
	public Role getRoleByCod(String cod) {
		return this.roleRepository.findByCod(cod);
	}
	
}
