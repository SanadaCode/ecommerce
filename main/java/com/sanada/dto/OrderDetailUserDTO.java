package com.sanada.dto;

public class OrderDetailUserDTO {
	
	private int id;
	private String name;
	private int quantity;
	private float price;
	private String img;
	private String desc;
	private String state;

	public OrderDetailUserDTO(int id, String name, int quantity, float price, String img, String desc, String state) {
		this.id = id;
		this.name = name;
		this.quantity = quantity;
		this.price = price;
		this.img = img;
		this.desc = desc;
		this.state = state;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public OrderDetailUserDTO() {
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
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
	
	public float getPrice() {
		return price;
	}
	
	public void setPrice(float price) {
		this.price = price;
	}
	
}
