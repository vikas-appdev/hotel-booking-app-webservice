package com.sdigitizers.hotel.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sdigitizers.hotel.codec.BookingStatus;
import com.sdigitizers.hotel.codec.FeedbackStatus;
import com.sdigitizers.hotel.codec.PaymentStatus;

@Entity
public class Booking {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
	@SequenceGenerator(initialValue = 100000, name = "seq")
	private Integer id;

	@ManyToOne
	private Room room;
	@JsonIgnore
	@OneToOne(mappedBy = "booking")
	private ReportIssue issue;

	

	@ManyToOne
	private User user;
	// @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@CreationTimestamp
	private LocalDateTime time;
	// @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime fromTime;
	// @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime uptoTime;
	private LocalDateTime checkinTime;
	private LocalDateTime checkoutTime;

	

	private String bookedFor;
	private String countryCode;
	private double price;
	private double discountAmount;
	private long phone;
	private String email;

	@OneToOne(mappedBy = "booking")
	private PromocodeTransaction transaction;

	@OneToOne(mappedBy = "booking")
	private BookingReview bookingReview;

	private String specialNote;
	private BookingStatus status;

	// Updated Field
	private PaymentStatus paymentStatus;
	@Column(columnDefinition = "boolean default 0")
	private boolean isBusinessTrip;
	private String GSTIN;
	private String legalName;
	private double walletMoneyUsed;
	private FeedbackStatus feedbackStatus;
	private double promoCodeAmountUsed;
	
	public ReportIssue getIssue() {
		return issue;
	}

	public void setIssue(ReportIssue issue) {
		this.issue = issue;
	}

	public double getPromoCodeAmountUsed() {
		return promoCodeAmountUsed;
	}

	public void setPromoCodeAmountUsed(double promoCodeAmountUsed) {
		this.promoCodeAmountUsed = promoCodeAmountUsed;
	}

	public PromocodeTransaction getTransaction() {
		return transaction;
	}

	public void setTransaction(PromocodeTransaction transaction) {
		this.transaction = transaction;
	}

	public Integer getId() {
		return id;
	}
	
	public LocalDateTime getCheckinTime() {
		return checkinTime;
	}

	public void setCheckinTime(LocalDateTime checkinTime) {
		this.checkinTime = checkinTime;
	}

	public LocalDateTime getCheckoutTime() {
		return checkoutTime;
	}

	public void setCheckoutTime(LocalDateTime checkoutTime) {
		this.checkoutTime = checkoutTime;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getIdString() {
		return String.format("%06d", id);
	}

	public Room getRoom() {
		return room;
	}
	
	public String getLatitude() {
		return room.getHotel().getAddress().getLatitude();
	}
	
	public String getLongitude() {
		return room.getHotel().getAddress().getLongitude();
	}
	
	public long getPhone1() {
		return room.getHotel().getPhone1();
	}

	public void setRoom(Room room) {
		this.room = room;
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

	public PaymentStatus getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(PaymentStatus paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public boolean isBusinessTrip() {
		return isBusinessTrip;
	}

	public void setBusinessTrip(boolean isBusinessTrip) {
		this.isBusinessTrip = isBusinessTrip;
	}

	public String getGSTIN() {
		return GSTIN;
	}

	public void setGSTIN(String gSTIN) {
		GSTIN = gSTIN;
	}

	public String getLegalName() {
		return legalName;
	}

	public void setLegalName(String legalName) {
		this.legalName = legalName;
	}

	public double getWalletMoneyUsed() {
		return walletMoneyUsed;
	}

	public void setWalletMoneyUsed(double walletMoneyUsed) {
		this.walletMoneyUsed = walletMoneyUsed;
	}

	public double getDiscountAmount() {
		return discountAmount;
	}

	public FeedbackStatus getFeedbackStatus() {
		return feedbackStatus;
	}

	public void setFeedbackStatus(FeedbackStatus feedbackStatus) {
		this.feedbackStatus = feedbackStatus;
	}

}