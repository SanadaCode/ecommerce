package com.sanada.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.sanada.dto.LoginDTO;
import com.sanada.dto.UserDTO;
import com.sanada.error.UserNotFoundException;
import com.sanada.login.LoginRepository;
import com.sanada.utility.Mapper;

@Service
public class LoginService {
	
	private LoginRepository loginRepository;

	@Autowired
	public LoginService(LoginRepository loginRepository) {
		this.loginRepository = loginRepository;
	}
	
	public LoginDTO login(String mail, String pass) {
		UserDTO tempUser =Mapper.UserToUserDTO(this.loginRepository.findByCustomer(mail, pass));
		
		if(tempUser == null) {
			throw new UserNotFoundException("User not found ");
		}
		LoginDTO tempLog;
		if(tempUser.getInfo() != null) {
			
			tempLog= new LoginDTO(tempUser.getId(),tempUser.getRole().getCod()
					,tempUser.getInfo().getFirstName(), tempUser.getInfo().getLastName());
			if(tempUser.getInfo().getImage() != null) {
				tempLog.setImage(tempUser.getInfo().getImage());
			}
		}else{
			tempLog= new LoginDTO(tempUser.getId(),tempUser.getRole().getCod()
					,null, null);
		}
		
		return tempLog;
	}
}
