package com.sanada.dto;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.sanada.entity.Role;

public class RoleDTO {

    
    
    private String cod;
    
   
    private String desc;

    public RoleDTO() {
    }

	public RoleDTO(String cod, String desc) {
		this.cod = cod;
		this.desc = desc;
	}

	public String getCod() {
		return cod;
	}

	public void setCod(String cod) {
		this.cod = cod;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	@Override
	public String toString() {
		return "Role [cod=" + cod + ", desc=" + desc + "]";
	}
	
}
