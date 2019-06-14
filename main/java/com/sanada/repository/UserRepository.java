package com.sanada.repository;

import javax.persistence.NamedNativeQuery;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sanada.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	
	User findById(int id);
	
}
