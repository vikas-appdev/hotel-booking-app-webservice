package com.sdigitizers.hotel.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class BookingReview implements Serializable {

	@Id
	@GeneratedValue
	private Long Id;
	@OneToOne
	@JsonIgnore
	private Booking booking;
	private LocalDateTime time;
	private int rating;
	private String reviewText;

	public int getBookingId() {
		
		return booking.getId();
	}
	
	public String getBookingUserName() {
		return booking.getUser().getName();
	}
	
	public String getBookingUserImageUrl(){
		return booking.getUser().getImageLink();
	}
	
	

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public Booking getBooking() {
		return booking;
	}

	public void setBooking(Booking booking) {
		this.booking = booking;
	}

	public LocalDateTime getTime() {
		return time;
	}

	public void setTime(LocalDateTime time) {
		this.time = time;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getReviewText() {
		return reviewText;
	}

	public void setReviewText(String reviewText) {
		this.reviewText = reviewText;
	}

}
