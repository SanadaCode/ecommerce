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
@Table(name="info", catalog="ecommerce")
public class InformazioniUtente {

    @Id
	@Column(name="ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
    @Column(name="UserLastName", nullable=false)
    private String lastName;

    @Column(name="UserFirstName", nullable=false)
    private String firstName;

    @Column(name="Address", nullable=false)
    private String address;

    @Column(name="Country", nullable=false)
    private String country;

    @Column(name="City", nullable=false)
    private String city;

    @Column(name="PhoneNumber")
    private String phone;

    @Column(name="Cap", nullable=false)
	private String cap;
	
    @Column(name="Image")
    private String image;
	

	public InformazioniUtente() {
	}


	public InformazioniUtente(int id, String lastName, String firstName, String address, String country, String city,
			String phone, String cap, String image) {
		super();
		this.id = id;
		this.lastName = lastName;
		this.firstName = firstName;
		this.address = address;
		this.country = country;
		this.city = city;
		this.phone = phone;
		this.cap = cap;
		this.image = image;
	}
	
	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String fistName) {
		this.firstName = fistName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCap() {
		return cap;
	}

	public void setCap(String cap) {
		this.cap = cap;
	}

	@Override
	public String toString() {
		return "Info [id=" + id + ", lastName=" + lastName + ", fistName=" + firstName + ", address=" + address
				+ ", country=" + country + ", city=" + city + ", phone=" + phone + ", cap=" + cap + "]";
	}
    
}
