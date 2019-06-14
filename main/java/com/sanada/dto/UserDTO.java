package com.sanada.dto;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.sanada.entity.Role;
import com.sanada.entity.User;

public class UserDTO {
 	
    private int id;
    
    private String email;
   
    private RoleDTO role;
    
    private InformationUserDTO info;

	public UserDTO() {
	}
	
	public UserDTO(int id, String email) {
		this.id = id;
		this.email = email;
	}
	
	public RoleDTO getRole() {
		return role;
	}

	public void setRole(RoleDTO role) {
		this.role = role;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
	public InformationUserDTO getInfo() {
		return info;
	}

	public void setInfo(InformationUserDTO info) {
		this.info = info;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + "]";
	}
	
}
