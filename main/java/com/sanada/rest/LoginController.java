package com.sanada.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sanada.dto.InformationUserDTO;
import com.sanada.dto.LoginDTO;
import com.sanada.error.UserNotFoundException;
import com.sanada.service.LoginService;

@RestController
@RequestMapping("/login")
@CrossOrigin(origins = "http://localhost:4200")
public class LoginController {
	
	private LoginService loginService;

	@Autowired
	public LoginController(LoginService loginService) {
		this.loginService = loginService;
	}
	
	
	@PostMapping("/log")
	public ResponseEntity<LoginDTO> login(@RequestParam("mail") String mail, 
			@RequestParam("pass") String pass ) {
		
		return new ResponseEntity<LoginDTO>(
				this.loginService.login(mail,pass),
					null,HttpStatus.OK);
		
		
	}
}
