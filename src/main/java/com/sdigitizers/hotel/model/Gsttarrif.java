package com.sdigitizers.hotel.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Gsttarrif {
	@Id
	@GeneratedValue
	private Integer id;
	private double minmumAmount;
	private double maximumAmount;
	private int gstPercentage;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public double getMinmumAmount() {
		return minmumAmount;
	}
	public void setMinmumAmount(double minmumAmount) {
		this.minmumAmount = minmumAmount;
	}
	public double getMaximumAmount() {
		return maximumAmount;
	}
	public void setMaximumAmount(double maximumAmount) {
		this.maximumAmount = maximumAmount;
	}
	public int getGstPercentage() {
		return gstPercentage;
	}
	public void setGstPercentage(int gstPercentage) {
		this.gstPercentage = gstPercentage;
	}
	
	

}
