package com.sdigitizers.hotel.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;

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
	private double rating;
	@Embedded
	private Address address;
	@Embedded
	private BankDetails bankDetails;
	
	@Column(columnDefinition="tinyint(1) default 0")
	private boolean paymentPreference;
	
	
	
	public boolean isPaymentPreference() {
		return paymentPreference;
	}

	public void setPaymentPreference(boolean paymentPreference) {
		this.paymentPreference = paymentPreference;
	}

	public BankDetails getBankDetails() {
		return bankDetails;
	}

	public void setBankDetails(BankDetails bankDetails) {
		this.bankDetails = bankDetails;
	}

	private String description;
	@OneToMany(mappedBy = "hotel")
	private List<Room> rooms;
	@OneToMany(mappedBy="hotel")
	private List<HotelImage> images;
	
	//Added field
	private boolean status;
	
	@CreationTimestamp
	private LocalDateTime hotelCreation;
	

	public LocalDateTime getHotelCreation() {
		return hotelCreation;
	}

	public void setHotelCreation(LocalDateTime hotelCreation) {
		this.hotelCreation = hotelCreation;
	}

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

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Hotel other = (Hotel) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
	
	

}
