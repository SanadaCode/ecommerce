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
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="users", catalog="ecommerce")
public class User {
    
    	
	@Id
	@Column(name="ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    
    @Column(name="UserEmail", nullable=false, unique=true)
    private String email;
    
    @Column(name="UserPassword", nullable=false)
    private String password;
    
    @ManyToOne(cascade= CascadeType.ALL, fetch= FetchType.LAZY)
    @JoinColumn(name="Role_ID",nullable=false)
    private Role role;
  
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="Info_Id")
	private InformazioniUtente info;

	public User() {
	}
	
	public User(String email, String password) {
		this.email = email;
		this.password = password;
	}
	
	public User(int id, String email, String password) {
		this.id = id;
		this.email = email;
		this.password = password;
	}
	
	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public InformazioniUtente getInfo() {
		return info;
	}

	public void setInfo(InformazioniUtente info) {
		this.info = info;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + ", password=" + password + "]";
	}

	
    
	
	
    
}
