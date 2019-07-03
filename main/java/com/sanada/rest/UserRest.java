package com.sanada.rest;

import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sanada.dto.InformationUserDTO;
import com.sanada.dto.LoginDTO;
import com.sanada.error.UserNotFoundException;
import com.sanada.service.UserService;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:4200")
public class UserRest {
	
	
	@Autowired
	private UserService userService;
	

	@PostMapping("/signup")
	public ResponseEntity<LoginDTO> signUp(@RequestParam("email") String mail, @RequestParam("pass") String pass) {
		
		return new ResponseEntity<LoginDTO>(this.userService.addUser(mail, pass),null, HttpStatus.OK);
		
	}

	@PostMapping("/save")
	public ResponseEntity<InformationUserDTO> saveInformationUser(@RequestParam("id") int id, 
			@RequestBody InformationUserDTO info) {
				System.out.println("save");
				if(this.userService.foundUser(id)) {
					return new ResponseEntity<InformationUserDTO>(
							this.userService.saveInformatioUser(id, info, null, null),
							null,
							HttpStatus.OK);
				}else {
					throw new UserNotFoundException("User not found ");
				}
		
	}
	
	@PutMapping("/edit")
	public ResponseEntity<InformationUserDTO> editInformationUser(@RequestParam("id") int id,
			@RequestParam("name") String name,
			@RequestParam("type") String type,
			@RequestBody InformationUserDTO info) {
		System.out.println("image: "+info.getImage());
		System.out.println("edit");
		if(this.userService.foundUser(id)) {
			return new ResponseEntity<InformationUserDTO>(
					this.userService.saveInformatioUser(id, info, name , type),
					null,
					HttpStatus.OK);
		}else {
			throw new UserNotFoundException("User not found ");
		}
		
	}
	
	
	@GetMapping("/profile")
	public ResponseEntity<InformationUserDTO> profileUser(@RequestParam("id") int id) {
		if(this.userService.foundUser(id)) {
			return new ResponseEntity<InformationUserDTO>(
					this.userService.getInformationUser(id),
					null,
					HttpStatus.OK);
		}else {
			throw new UserNotFoundException("User not found ");
		}
		
	}
}
