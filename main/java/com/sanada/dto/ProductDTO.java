package com.sanada.dto;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.sanada.entity.Product;

public class ProductDTO {
	
    private String productName;

    private float productPrice;

    private String desc;

    private String img;

    private int quantity;

	public ProductDTO(String productName, float productPrice, String desc, String img, int quantity) {
		this.productName = productName;
		this.productPrice = productPrice;
		this.desc = desc;
		this.img = img;
		this.quantity = quantity;
	}
	
	
	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public float getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(float productPrice) {
		this.productPrice = productPrice;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	
	@Override
	public String toString() {
		return "ProductDTO [productName=" + productName + ", productPrice=" + productPrice + ", desc=" + desc + ", img="
				+ img + ", quantity=" + quantity + "]";
	}
    
    
    
    
}
