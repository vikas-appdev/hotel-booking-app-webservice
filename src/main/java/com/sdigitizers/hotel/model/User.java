package com.sdigitizers.hotel.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class User {

	@Id
	@GeneratedValue
	private Integer id;
	private boolean active;
	private String password;
	
	private String imageLink;
	private String name;
	private String countryCode;
	private long phone;
	private String email;
	private LocalDate dateOfBirth;
	@Embedded
	private Address address;
	
	@OneToMany(mappedBy="user")
	@JsonIgnore
	private List<Booking> bookings;
	
	private LocalDateTime accountCreationTime;
	
	public Integer getId() {
		return id;
	}

	public List<Booking> getBookings() {
		return bookings;
	}

	public void setBookings(List<Booking> bookings) {
		this.bookings = bookings;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	
	

	

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getImageLink() {
		return imageLink;
	}

	public void setImageLink(String imageLink) {
		this.imageLink = imageLink;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public long getPhone() {
		return phone;
	}

	public void setPhone(long phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
	
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	public LocalDateTime getAccountCreationTime() {
		return accountCreationTime;
	}

	public void setAccountCreationTime(LocalDateTime accountCreationTime) {
		this.accountCreationTime = accountCreationTime;
	}
	
	/////
	
	public int getAge() {
		return LocalDate.now().getYear()-getDateOfBirth().getYear();
	}
	
	

}