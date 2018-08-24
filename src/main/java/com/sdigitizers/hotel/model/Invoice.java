package com.sdigitizers.hotel.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Invoice {

	@Id
	private Integer id;
	@OneToOne
	private Booking booking;
	private LocalDate date;
	private double price;
	private double qty;
	private double discountAmount;
	private double gstRate;
	private int sac;
	
	private String clientName;
	private String clientAddress;
	private String phone;
	private String email;
	
	private boolean businessTrip;
	private String gstin;
	
	public String getInvoiceId() {
		return String.format("%06d", id);
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	/*public Booking getBooking() {
		return booking;
	}
	public void setBooking(Booking booking) {
		this.booking = booking;
	}*/
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getQty() {
		return qty;
	}
	public void setQty(double qty) {
		this.qty = qty;
	}
	public double getDiscountAmount() {
		return discountAmount;
	}
	public void setDiscountAmount(double discountAmount) {
		this.discountAmount = discountAmount;
	}
	public double getGstRate() {
		return gstRate;
	}
	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public int getSac() {
		return sac;
	}

	public void setSac(int sac) {
		this.sac = sac;
	}

	public void setGstRate(double gstRate) {
		this.gstRate = gstRate;
	}
	public String getClientName() {
		return clientName;
	}
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	public String getClientAddress() {
		return clientAddress;
	}
	public void setClientAddress(String clientAddress) {
		this.clientAddress = clientAddress;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public boolean isBusinessTrip() {
		return businessTrip;
	}
	public void setBusinessTrip(boolean businessTrip) {
		this.businessTrip = businessTrip;
	}
	public String getGstin() {
		return gstin;
	}
	public void setGstin(String gstin) {
		this.gstin = gstin;
	}
	
	
}
