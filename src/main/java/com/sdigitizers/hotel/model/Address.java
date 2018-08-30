package com.sdigitizers.hotel.model;

import javax.persistence.Embeddable;

@Embeddable
public class Address {

	private String street;
	private String city;
	private String state;
	private int pincode;
	private String country;
	

	public String getStreet() {
		return street;
	}

	public Address setStreet(String street) {
		this.street = street;
		return this;
	}

	public String getCity() {
		return city;
	}

	public Address setCity(String city) {
		this.city = city;
		return this;
	}

	public String getState() {
		return state;
	}

	public Address setState(String state) {
		this.state = state;
		return this;
	}

	public int getPincode() {
		return pincode;
	}

	public Address setPincode(int pincode) {
		this.pincode = pincode;
		return this;
	}

	public String getCountry() {
		return country;
	}

	public Address setCountry(String country) {
		this.country = country;
		return this;
	}

	
	@Override
	public String toString() {
		return street+", "+city+", "+state+", ["+pincode+"], "+country;
	}

	
	
}
