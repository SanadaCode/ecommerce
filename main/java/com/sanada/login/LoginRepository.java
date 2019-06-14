package com.sanada.login;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sanada.entity.User;


public interface LoginRepository extends JpaRepository<User, Integer> {

	@Query("SELECT u FROM User u where u.email =:mail and u.password=:pass")
	User findByCustomer(String mail, String pass); 
	
}
