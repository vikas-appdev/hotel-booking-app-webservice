package com.sdigitizers.hotel.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.hibernate.annotations.CreationTimestamp;

import com.sdigitizers.hotel.codec.PaymentMode;
import com.sdigitizers.hotel.codec.TransactionStatus;
import com.sdigitizers.hotel.codec.TransactionType;

@Entity
public class Transaction {
	@Id
	@GeneratedValue
	private Long id;
	@OneToOne
	private Booking booking;
	@CreationTimestamp
	private LocalDateTime dateTime;
	private double amount;
	@Enumerated
	@Column(columnDefinition="TINYINT", length=1)
	private TransactionType txnType;
	@Enumerated
	@Column(columnDefinition="TINYINT", length=1)
	private TransactionStatus txnStatus;
	@Enumerated
	@Column(columnDefinition="TINYINT", length=1)
	private PaymentMode paymentMode;
	private String gateway;
	private String gateway_txn_ref;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Booking getBooking() {
		return booking;
	}
	public void setBooking(Booking booking) {
		this.booking = booking;
	}
	public LocalDateTime getDateTime() {
		return dateTime;
	}
	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public TransactionType getTxnType() {
		return txnType;
	}
	public void setTxnType(TransactionType txnType) {
		this.txnType = txnType;
	}
	public TransactionStatus getTxnStatus() {
		return txnStatus;
	}
	public void setTxnStatus(TransactionStatus txnStatus) {
		this.txnStatus = txnStatus;
	}
	public PaymentMode getPaymentMode() {
		return paymentMode;
	}
	public void setPaymentMode(PaymentMode paymentMode) {
		this.paymentMode = paymentMode;
	}
	public String getGateway() {
		return gateway;
	}
	public void setGateway(String gateway) {
		this.gateway = gateway;
	}
	public String getGateway_txn_ref() {
		return gateway_txn_ref;
	}
	public void setGateway_txn_ref(String gateway_txn_ref) {
		this.gateway_txn_ref = gateway_txn_ref;
	}
	
	

}
