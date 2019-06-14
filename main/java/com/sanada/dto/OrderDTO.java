package com.sanada.dto;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.sanada.entity.StateOrder;
import com.sanada.entity.Transaction;
import com.sanada.entity.User;

public class OrderDTO {

	private int id;
	
    private Date date;

    private StateOrderDTO state;
    
    private TransactionDTO transaction;
    
    

	public OrderDTO() {
	}

	public OrderDTO(int id, Date date, StateOrderDTO state, TransactionDTO transaction) {
		this.id = id;
		this.date = date;
		this.state = state;
		this.transaction = transaction;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public StateOrderDTO getState() {
		return state;
	}

	public void setState(StateOrderDTO state) {
		this.state = state;
	}

	public TransactionDTO getTransaction() {
		return transaction;
	}

	public void setTransaction(TransactionDTO transaction) {
		this.transaction = transaction;
	}
	
	
    
    

}
