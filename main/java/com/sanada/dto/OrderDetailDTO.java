package com.sanada.dto;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.sanada.entity.Order;
import com.sanada.entity.Product;

public class OrderDetailDTO {
  
    private int amount;
    
    private String state;
    
    private OrderDTO order;
    
    private ProductDTO product;
    

	public OrderDetailDTO() {
	}
	
	public OrderDetailDTO(int amount, String state, OrderDTO order, ProductDTO product) {
		this.amount = amount;
		this.state = state;
		this.order = order;
		this.product = product;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public OrderDTO getOrder() {
		return order;
	}

	public void setOrder(OrderDTO order) {
		this.order = order;
	}

	public ProductDTO getProduct() {
		return product;
	}

	public void setProduct(ProductDTO product) {
		this.product = product;
	}   

}
