package com.sdigitizers.hotel.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class PromocodeTransaction {
	@Id
	@GeneratedValue
	private Integer id;
	private LocalDateTime time;
	@ManyToOne
	private PromoCode promoCode;
	@ManyToOne
	private User user;
	@OneToOne
	private Booking booking;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public LocalDateTime getTime() {
		return time;
	}
	public void setTime(LocalDateTime time) {
		this.time = time;
	}
	public PromoCode getPromoCode() {
		return promoCode;
	}
	public void setPromoCode(PromoCode promoCode) {
		this.promoCode = promoCode;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Booking getBooking() {
		return booking;
	}
	public void setBooking(Booking booking) {
		this.booking = booking;
	}
	
	
	
	

}
