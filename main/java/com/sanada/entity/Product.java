package com.sanada.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="products", catalog="ecommerce")
public class Product {
	
	@Id
	@Column(name="ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
    @Column(name="ProductName", nullable=false, unique=true)
    private String productName;

    @Column(name="ProductPrice", nullable=false)
    private float productPrice;

    @Column(name="ProductDesc")
    private String desc;

    @Column(name="ProductImage")
    private String img;

    @Column(name="ProductQuantity",nullable=false)
    private int quantity;
    
    @Column(name="venditore_id",nullable=false)
    private int venditoreId;
    
    @Column(name="deleted", nullable=false)
    private boolean deleted;
    	
    public Product() {}
  
	public Product(int id, String productName, float productPrice) {
		this.id = id;
		this.productName = productName;
		this.productPrice = productPrice;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public int getVenditoreId() {
		return venditoreId;
	}

	public void setVenditoreId(int venditoreId) {
		this.venditoreId = venditoreId;
	}

	public boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", productName=" + productName + ", productPrice=" + productPrice + ", desc="
				+ desc + ", img=" + img + ", quantity=" + quantity + "]";
	}
}
