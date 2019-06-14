package com.sanada.entity;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="orders", catalog="ecommerce")
public class Order {

    @Id
	@Column(name="ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="DateOrder")
    private Date date;

    @ManyToOne(cascade= CascadeType.ALL, fetch= FetchType.LAZY)
    @JoinColumn(name="User_ID")
    private User user;

    @ManyToOne(cascade= CascadeType.ALL, fetch= FetchType.LAZY)
    @JoinColumn(name="State_ID")
    private StateOrder state;
    
    @OneToOne(cascade= CascadeType.ALL, fetch= FetchType.LAZY)
    @JoinColumn(name="Transaction_ID")
    private Transaction transaction;

	public Order() {
	}

	public Order(int id, Timestamp date) {
		this.id = id;
		this.date = date;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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
	
	public StateOrder getState() {
		return state;
	}

	public void setState(StateOrder state) {
		this.state = state;
	}

	public Transaction getTransaction() {
		return transaction;
	}

	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", date=" + date + ", state=" + state + "]";
	}
    
}



