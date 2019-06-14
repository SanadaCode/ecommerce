package com.sanada.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.sanada.dto.InformationUserDTO;
import com.sanada.dto.LoginDTO;
import com.sanada.entity.InformazioniUtente;
import com.sanada.entity.User;
import com.sanada.error.UserNotFoundException;
import com.sanada.model.RoleCod;
import com.sanada.repository.UserRepository;
import com.sanada.utility.Mapper;

@Service
public class UserService {
	
	private UserRepository userRepository;
	private RoleService roleService;
	
	@Autowired
	public UserService(UserRepository userRepository,RoleService roleService) {
		this.userRepository = userRepository;
		this.roleService=roleService;
	}
	
	public LoginDTO addUser(String email, String pass) {
		User user= null;
		if(email != "" && pass!="") {
			user= new User(email,pass);
			user.setRole(this.roleService.getRoleByCod(RoleCod.CLIENTE.getCod()));
		}
		this.userRepository.save(user);
		LoginDTO log = new LoginDTO(user.getId(),user.getRole().getCod());
		return log;
	}
	
	public InformationUserDTO saveInformatioUser(int id, InformationUserDTO info) {
		
		User user = this.userRepository.findById(id);
		InformazioniUtente infoUtente = new InformazioniUtente();
		if(user.getInfo() != null) {
			infoUtente = user.getInfo();
		}
		Mapper.getInformationFromInformationDTO(info, infoUtente);
		user.setInfo(infoUtente);
		this.userRepository.save(user);
		return info;
		
	}

	public InformationUserDTO getInformationUser(int id) {
		User user = this.userRepository.findById(id);
		InformationUserDTO info= Mapper.InformationToInformationUserDTO(user.getInfo());
		return info;
	}

	public boolean foundUser(int id) {
		return this.userRepository.existsById(id);
	}
	
	public boolean authorizedSeller(int id) {
		if(foundUser(id)) {
			User tempUser = this.userRepository.findById(id);
			if(!tempUser.getRole().getCod().equals(RoleCod.VENDITORE.getCod())){
				return false;
			}else {
				return true;
			}
		}else {
			throw new UserNotFoundException("User not found!");
		}
	}
}
