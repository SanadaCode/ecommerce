package com.sanada.dto;

public class LoginDTO {
	
	private int id;
	private String cod;
	private String name;
	private String surname;
	
	
	public LoginDTO() {
	}

	public LoginDTO(int id, String cod) {
		this.id = id;
		this.cod = cod;
	}

	
	public LoginDTO(int id, String cod, String name, String surname) {
		this.id = id;
		this.cod = cod;
		this.name = name;
		this.surname = surname;
	}

	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCod() {
		return cod;
	}

	public void setCod(String cod) {
		this.cod = cod;
	}
	
	
	
}
