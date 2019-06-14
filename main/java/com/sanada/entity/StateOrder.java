package com.sanada.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="stateoforders", catalog="ecommerce")
public class StateOrder {

    @Id
	@Column(name="ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    
    @Column(name="cod", nullable=false, unique=true)
    private String cod;

    @Column(name="desc")
    private String desc;

	public StateOrder(int id, String cod, String desc) {
		super();
		this.id = id;
		this.cod = cod;
		this.desc = desc;
	}

	public StateOrder() {
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

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	@Override
	public String toString() {
		return "StateOrder [id=" + id + ", cod=" + cod + ", desc=" + desc + "]";
	}

	
	
    
}
