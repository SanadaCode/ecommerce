package com.sanada.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="transactions", catalog="ecommerce")
public class Transaction {

    @Id
	@Column(name="ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    

	@Column(name="TotalPrice")
    private float price;

	public Transaction() {
	}
	
	public Transaction(int id, float price) {
		this.id = id;
		this.price = price;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Transaction [id=" + id + ", price=" + price + "]";
	}
    
    

}
