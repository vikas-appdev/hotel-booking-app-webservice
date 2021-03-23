package com.sdigitizers.hotel.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class User {

	@Id
	@GeneratedValue
	private Integer id;
	private boolean active;
	private String password;
	private String gender;
	private String imageLink;
	private String name;
	private String countryCode;
	@Column(unique=true)
	private long phone;
	@Column(unique=true)
	@Email
	private String email;
	// @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate dateOfBirth;
	@Embedded
	private Address address;

	@OneToMany(mappedBy = "user")
	@JsonIgnore
	private List<Booking> bookings;
	// @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	@JsonIgnore
	@CreationTimestamp
	private LocalDateTime accountCreationTime;
	
	@JsonIgnore
	@OneToMany(mappedBy="user")
	private List<FabRoom> fabroom;
	
	//Updated Field
	private double walletBalance;
	@Column(columnDefinition="CHAR(6)", length=6)
	private String referralCode;
	@OneToMany(mappedBy="user")
	private List<CustomerWalletTxn> customerWalletTxn;
	
	@OneToMany(mappedBy="user")
	private List<PromocodeTransaction> promoCodeTransactions;
	
	@OneToOne(mappedBy="user")
	private Agent agent;
	
	private int userType;
	
	

	public int getUserType() {
		return userType;
	}

	public void setUserType(int userType) {
		this.userType = userType;
	}

	public Agent getAgent() {
		return agent;
	}

	public void setAgent(Agent agent) {
		this.agent = agent;
	}

	

	public List<PromocodeTransaction> getTransactions() {
		return promoCodeTransactions;
	}

	public void setTransactions(List<PromocodeTransaction> transactions) {
		this.promoCodeTransactions = transactions;
	}

	public List<FabRoom> getFabroom() {
		return fabroom;
	}

	public void setFabroom(List<FabRoom> fabroom) {
		this.fabroom = fabroom;
	}

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
		return LocalDate.now().getYear() - getDateOfBirth().getYear();
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public double getWalletBalance() {
		return walletBalance;
	}

	public void setWalletBalance(double walletBalance) {
		this.walletBalance = walletBalance;
	}

	public String getReferralCode() {
		return referralCode;
	}

	public void setReferralCode(String referralCode) {
		this.referralCode = referralCode;
	}

	
	public List<CustomerWalletTxn> getCustomerWalletTxn() {
		return customerWalletTxn;
	}

	public void setCustomerWalletTxn(List<CustomerWalletTxn> customerWalletTxn) {
		this.customerWalletTxn = customerWalletTxn;
	}

}