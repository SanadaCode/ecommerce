package com.sanada.dto;

import java.util.Date;

public class OrderSellerDTO {
	
	private int id;
	private String name;
	private int quantity;
	private String address;
	private String city;
	private String country;
	private String state;
	private Date date;
	
	
	public OrderSellerDTO() {
	}
	
	public OrderSellerDTO(int id, String name, int quantity, String address, String city, String country, String state,
			Date date) {
		super();
		this.id = id;
		this.name = name;
		this.quantity = quantity;
		this.address = address;
		this.city = city;
		this.country = country;
		this.state = state;
		this.date = date;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getQuantity() {
		return quantity;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCity() {
		return city;
	}
	
	public void setCity(String city) {
		this.city = city;
	}
	
	public String getCountry() {
		return country;
	}
	
	public void setCountry(String country) {
		this.country = country;
	}
	
	

}
