package com.sdigitizers.hotel.model;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sdigitizers.hotel.codec.PaymentMode;
import com.sdigitizers.hotel.codec.TransactionType;

@Entity
public class CustomerWalletTxn {
	@Id
	@GeneratedValue
	private Integer txnId;
	
	@JsonIgnore
	@ManyToOne
	private User user;
	
	@CreationTimestamp
	private LocalDate date;
	
	@Enumerated
	private TransactionType txnType;
	
	private double amount;
	
//	@Enumerated
//	private PaymentMode paymentMode;
	
	private String txnRef;
	
	private String remarks;

	public Integer getTxnId() {
		return txnId;
	}

	public void setTxnId(Integer txnId) {
		this.txnId = txnId;
	}

	
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public TransactionType getTxnType() {
		return txnType;
	}

	public void setTxnType(TransactionType txnType) {
		this.txnType = txnType;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	

	public String getTxnRef() {
		return txnRef;
	}

	public void setTxnRef(String txnRef) {
		this.txnRef = txnRef;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	
	

}
