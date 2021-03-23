package com.sdigitizers.hotel.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Promotion {
	
	@Id
	@GeneratedValue
	private Integer id;
    private String imageLink;
    private boolean active;
    @ManyToOne
    private PromoCode promoCode;
    private String city;
    
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getImageLink() {
		return imageLink;
	}
	public void setImageLink(String imageLink) {
		this.imageLink = imageLink;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public PromoCode getPromoCode() {
		return promoCode;
	}
	public void setPromoCode(PromoCode promoCode) {
		this.promoCode = promoCode;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
    
    

}
