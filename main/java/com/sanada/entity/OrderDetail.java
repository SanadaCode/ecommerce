package com.sanada.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="order_details", catalog="ecommerce")
public class OrderDetail {

    @Id
	@Column(name="ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    
    @Column(name="Amount")
    private int amount;
    
    @Column(name="state")
    private String state;
    
    @ManyToOne(cascade= {CascadeType.DETACH,
    		CascadeType.MERGE, 
    		CascadeType.PERSIST,
    		CascadeType.REFRESH}, fetch= FetchType.LAZY)
    @JoinColumn(name="Order_ID")
    private Order order;
    
    @ManyToOne(cascade= {CascadeType.DETACH,
    		CascadeType.MERGE,
    		CascadeType.PERSIST,
    		CascadeType.REFRESH}, fetch= FetchType.LAZY)
    @JoinColumn(name="Product_ID")
    private Product product;

	public OrderDetail(int id, int amount) {
		this.id = id;
		this.amount = amount;
	}

	public OrderDetail() {
	}
	
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@Override
	public String toString() {
		return "OrderDetail [id=" + id + ", amount=" + amount + "]";
	}
	
}
