package com.sdigitizers.hotel.model;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sdigitizers.hotel.codec.PromoCodeType;

@Entity
public class PromoCode {
	
	@Id
	@GeneratedValue
	private Integer id;
	private String description;
	private boolean discountInPercentage;
	private double value;
	private double minAmount;
	private int maxTime;
	private LocalDateTime validFrom;
	private LocalDateTime validTill;
	private LocalTime timeStart;
	private LocalTime timeEnd;
	private PromoCodeType promoCodeType;
	private boolean status;
	@Column(unique=true)
	private String code;
	
	@OneToMany(mappedBy="promoCode")
	@JsonIgnore
	private List<Promotion> promotions;
	
	private double maxAmount;
	
	
	
	
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	@OneToMany(mappedBy="promoCode")
	private List<PromocodeTransaction> transaction;
	
	public List<PromocodeTransaction> getTransaction() {
		return transaction;
	}
	public void setTransaction(List<PromocodeTransaction> transaction) {
		this.transaction = transaction;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public boolean isDiscountInPercentage() {
		return discountInPercentage;
	}
	public void setDiscountInPercentage(boolean discountInPercentage) {
		this.discountInPercentage = discountInPercentage;
	}
	public double getValue() {
		return value;
	}
	public void setValue(double value) {
		this.value = value;
	}
	public double getMinAmount() {
		return minAmount;
	}
	public void setMinAmount(double minAmount) {
		this.minAmount = minAmount;
	}
	public int getMaxTime() {
		return maxTime;
	}
	public void setMaxTime(int maxTime) {
		this.maxTime = maxTime;
	}
	public LocalDateTime getValidFrom() {
		return validFrom;
	}
	public void setValidFrom(LocalDateTime validFrom) {
		this.validFrom = validFrom;
	}
	public LocalDateTime getValidTill() {
		return validTill;
	}
	public void setValidTill(LocalDateTime validTill) {
		this.validTill = validTill;
	}
	public LocalTime getTimeStart() {
		return timeStart;
	}
	public void setTimeStart(LocalTime timeStart) {
		this.timeStart = timeStart;
	}
	public LocalTime getTimeEnd() {
		return timeEnd;
	}
	public void setTimeEnd(LocalTime timeEnd) {
		this.timeEnd = timeEnd;
	}
	public PromoCodeType getPromoCodeType() {
		return promoCodeType;
	}
	public void setPromoCodeType(PromoCodeType promoCodeType) {
		this.promoCodeType = promoCodeType;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public double getMaxAmount() {
		return maxAmount;
	}
	public void setMaxAmount(double maxAmount) {
		this.maxAmount = maxAmount;
	}
	
	
	public List<Promotion> getPromotions() {
		return promotions;
	}
	public void setPromotions(List<Promotion> promotions) {
		this.promotions = promotions;
	}
	
	
	

}
