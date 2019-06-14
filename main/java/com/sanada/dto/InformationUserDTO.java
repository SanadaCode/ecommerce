package com.sanada.dto;

import com.sanada.entity.InformazioniUtente;

public class InformationUserDTO {
	
    private String lastName;

    private String firstName;

    private String address;

    private String country;

    private String city;

    private String phone;

	private String cap;
	
	
	public InformationUserDTO() {
	}

	public InformationUserDTO(String lastName, String firstName,
					String address, String country,
					String city, String phone,
					String cap){
		this.lastName = lastName;
		this.firstName = firstName;
		this.address = address;
		this.country = country;
		this.city = city;
		this.phone = phone;
		this.cap = cap;
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
		return "Info [lastName=" + lastName + ", fistName=" + firstName + ", address=" + address
				+ ", country=" + country + ", city=" + city + ", phone=" + phone + ", cap=" + cap + "]";
	}
	
}
