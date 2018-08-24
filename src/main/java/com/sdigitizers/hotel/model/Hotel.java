package com.sdigitizers.hotel.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Hotel {

	@Id
	@GeneratedValue
	private Integer id;
	private String password;
	private String name;
	private String contactPerson;
	private String email;
	private String countryCode;
	private long phone1;
	private long phone2;
	private String imageLink;
	private String locality;
	@Column(columnDefinition="text")
	private String terms;
	@Embedded
	private Address address;
	private String description;
	@OneToMany(mappedBy = "hotel")
	private List<Room> rooms;
	@OneToMany(mappedBy="hotel")
	private List<HotelImage> images;
	
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContactPerson() {
		return contactPerson;
	}

	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public long getPhone1() {
		return phone1;
	}

	public void setPhone1(long phone1) {
		this.phone1 = phone1;
	}

	public long getPhone2() {
		return phone2;
	}

	public void setPhone2(long phone2) {
		this.phone2 = phone2;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Room> getRooms() {
		return rooms;
	}

	public void setRooms(List<Room> rooms) {
		this.rooms = rooms;
	}

	public String getIdString() {
		return String.format("%04d", id);
	}
	
	public String getTerms() {
		return terms;
	}

	public void setTerms(String terms) {
		this.terms = terms;
	}

	public String getLocality() {
		return locality;
	}

	public void setLocality(String locality) {
		this.locality = locality;
	}

	public String getImageLink() {
		return imageLink;
	}

	public void setImageLink(String imageLink) {
		this.imageLink = imageLink;
	}

	public List<HotelImage> getImages() {
		return images;
	}

	public void setImages(List<HotelImage> images) {
		this.images = images;
	}

}
