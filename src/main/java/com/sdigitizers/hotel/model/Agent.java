package com.sdigitizers.hotel.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sdigitizers.hotel.codec.Income;
import com.sdigitizers.hotel.codec.Occupation;
import com.sdigitizers.hotel.codec.Qualification;
import com.sdigitizers.hotel.codec.RefferedByType;

@Entity
public class Agent {
	@Id
	@GeneratedValue
	private Integer id;
	private String agencyName;
	private long phone;
	private String email;
	private boolean maritalStatus;
	private Qualification qualification; //enum
	private Occupation occupation; //business, service
	private String pancardNumber;
	private String pancardHolderName;
	private long aadharcardNumber;
	private String aadharcardHolderName;
	private Income income;// 0-3,3-5,5-10,<10
	private String natureOfBusiness; //Travle or Others
	private LocalDate yearOfIncorporation;
	private RefferedByType refferedByType;//Distributers, sales, other
	private String refferedBy;
	private String otherPortalIfUsing;
	@OneToOne
	@JsonIgnore
	private User user;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getAgencyName() {
		return agencyName;
	}
	public void setAgencyName(String agencyName) {
		this.agencyName = agencyName;
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
	public boolean isMaritalStatus() {
		return maritalStatus;
	}
	public void setMaritalStatus(boolean maritalStatus) {
		this.maritalStatus = maritalStatus;
	}
	public Qualification getQualification() {
		return qualification;
	}
	public void setQualification(Qualification qualification) {
		this.qualification = qualification;
	}
	public Occupation getOccupation() {
		return occupation;
	}
	public void setOccupation(Occupation occupation) {
		this.occupation = occupation;
	}
	public String getPancardNumber() {
		return pancardNumber;
	}
	public void setPancardNumber(String pancardNumber) {
		this.pancardNumber = pancardNumber;
	}
	public String getPancardHolderName() {
		return pancardHolderName;
	}
	public void setPancardHolderName(String pancardHolderName) {
		this.pancardHolderName = pancardHolderName;
	}
	public long getAadharcardNumber() {
		return aadharcardNumber;
	}
	public void setAadharcardNumber(long aadharcardNumber) {
		this.aadharcardNumber = aadharcardNumber;
	}
	public String getAadharcardHolderName() {
		return aadharcardHolderName;
	}
	public void setAadharcardHolderName(String aadharcardHolderName) {
		this.aadharcardHolderName = aadharcardHolderName;
	}
	public Income getIncome() {
		return income;
	}
	public void setIncome(Income income) {
		this.income = income;
	}
	public String getNatureOfBusiness() {
		return natureOfBusiness;
	}
	public void setNatureOfBusiness(String natureOfBusiness) {
		this.natureOfBusiness = natureOfBusiness;
	}
	public LocalDate getYearOfIncorporation() {
		return yearOfIncorporation;
	}
	public void setYearOfIncorporation(LocalDate yearOfIncorporation) {
		this.yearOfIncorporation = yearOfIncorporation;
	}
	public RefferedByType getRefferedByType() {
		return refferedByType;
	}
	public void setRefferedByType(RefferedByType refferedByType) {
		this.refferedByType = refferedByType;
	}
	public String getRefferedBy() {
		return refferedBy;
	}
	public void setRefferedBy(String refferedBy) {
		this.refferedBy = refferedBy;
	}
	public String getOtherPortalIfUsing() {
		return otherPortalIfUsing;
	}
	public void setOtherPortalIfUsing(String otherPortalIfUsing) {
		this.otherPortalIfUsing = otherPortalIfUsing;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	

	

}
