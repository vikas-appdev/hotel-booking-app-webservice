package com.sdigitizers.hotel.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sdigitizers.hotel.codec.BookingStatus;

@Entity
public class Booking {

	@Id
	private Integer id;
	
	@ManyToOne
	@JsonIgnore
	private Room room;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JsonIgnore
	private User user;
	private LocalDateTime time;
	private LocalDateTime fromTime;
	private LocalDateTime uptoTime;
	private String bookedFor;
	private String countryCode;
	private double price;
	private double discountAmount;
	private long phone;
	private String email;
	
	@OneToOne(mappedBy="booking")
	private Invoice invoice;
	
	@OneToOne(mappedBy="booking")
	private BookingReview bookingReview;

	public Invoice getInvoice() {
		return invoice;
	}

	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}

	private String specialNote;
	private BookingStatus status;

	public String getIdString() {
		return String.format("%06d", id);
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	
	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public LocalDateTime getTime() {
		return time;
	}

	public void setTime(LocalDateTime time) {
		this.time = time;
	}

	public LocalDateTime getFromTime() {
		return fromTime;
	}

	public void setFromTime(LocalDateTime fromTime) {
		this.fromTime = fromTime;
	}

	public LocalDateTime getUptoTime() {
		return uptoTime;
	}

	public void setUptoTime(LocalDateTime uptoTime) {
		this.uptoTime = uptoTime;
	}

	public String getBookedFor() {
		return bookedFor;
	}

	public void setBookedFor(String bookedFor) {
		this.bookedFor = bookedFor;
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

	public String getSpecialNote() {
		return specialNote;
	}

	public void setSpecialNote(String specialNote) {
		this.specialNote = specialNote;
	}

	public BookingStatus getStatus() {
		return status;
	}

	public void setStatus(BookingStatus status) {
		this.status = status;
	}

	public double getPrice() {
		return price;
	}
	
	public BookingReview getBookingReview() {
		return bookingReview;
	}

	public void setBookingReview(BookingReview bookingReview) {
		this.bookingReview = bookingReview;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public void setDiscountAmount(double discountAmount) {
		this.discountAmount = discountAmount;
	}

	public double getDiscountedPrice() {
		return price - discountAmount;
	}
}