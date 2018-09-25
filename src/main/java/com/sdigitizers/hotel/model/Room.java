package com.sdigitizers.hotel.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreType;
import com.sdigitizers.hotel.codec.RoomCategory;

@Entity
public class Room {

	@Id
	@GeneratedValue
	private Integer id;
	private String name;
	private boolean active;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JsonIgnore
	private Hotel hotel;
	
	@OneToMany(mappedBy="room")
	private List<RoomImage> images;
	
	@OneToMany(mappedBy="room")
	@JsonIgnore
	private List<FabRoom> fabRoom;
	

	@OneToMany(mappedBy="room")
	@JsonIgnore
	private List<Booking> bookings;
	
	@Enumerated
	@Column(columnDefinition="TINYINT", length=1)
	private RoomCategory category;
	private double price;
	private boolean discountInPercentage;
	private double discountValue;

	private int noOfPersons;

	private boolean coupleAllowed;
	private boolean familyAllowed;

	private boolean ac;
	private boolean wifi;
	private boolean tv;
	private boolean service24_7;
	private boolean geyser;
	private boolean miniFridge;
	private boolean heater;
	private boolean parking;
	private boolean livingRoom;
	private boolean resturant;
	private boolean dining;
	private boolean cctv;
	private boolean complementryBreakfast;
	
	

	public Room() { }
	public Room(int id) { this.id = id; }
	
	public List<Booking> getBookings() {
		return bookings;
	}

	public void setBookings(List<Booking> bookings) {
		this.bookings = bookings;
	}


	public void setDiscountInPercentage(boolean discountInPercentage) {
		this.discountInPercentage = discountInPercentage;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getIdString() {
		return String.format("%05d", id);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String room) {
		this.name = room;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

	public RoomCategory getCategory() {
		return category;
	}

	public void setCategory(RoomCategory category) {
		this.category = category;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getDiscountValue() {
		return discountValue;
	}

	public void setDiscountValue(double discountValue) {
		this.discountValue = discountValue;
	}
	
	public boolean isDiscountInPercentage() {
		return discountInPercentage;
	}

	public void setDicountInPercentage(boolean discountInPercentage) {
		this.discountInPercentage = discountInPercentage;
	}

	public int getNoOfPersons() {
		return noOfPersons;
	}

	public void setNoOfPersons(int noOfPersons) {
		this.noOfPersons = noOfPersons;
	}

	public boolean isCoupleAllowed() {
		return coupleAllowed;
	}

	public void setCoupleAllowed(boolean coupleAllowed) {
		this.coupleAllowed = coupleAllowed;
	}

	public boolean isFamilyAllowed() {
		return familyAllowed;
	}

	public void setFamilyAllowed(boolean familyAllowed) {
		this.familyAllowed = familyAllowed;
	}

	public boolean isAc() {
		return ac;
	}

	public void setAc(boolean ac) {
		this.ac = ac;
	}

	public boolean isWifi() {
		return wifi;
	}

	public void setWifi(boolean wifi) {
		this.wifi = wifi;
	}

	public boolean isTv() {
		return tv;
	}

	public void setTv(boolean tv) {
		this.tv = tv;
	}

	public boolean isService24_7() {
		return service24_7;
	}

	public boolean isGeyser() {
		return geyser;
	}
	public void setGeyser(boolean geyser) {
		this.geyser = geyser;
	}
	public boolean isMiniFridge() {
		return miniFridge;
	}
	public void setMiniFridge(boolean miniFridge) {
		this.miniFridge = miniFridge;
	}
	public boolean isHeater() {
		return heater;
	}
	public void setHeater(boolean heater) {
		this.heater = heater;
	}
	public boolean isParking() {
		return parking;
	}
	public void setParking(boolean parking) {
		this.parking = parking;
	}
	public boolean isLivingRoom() {
		return livingRoom;
	}
	public void setLivingRoom(boolean livingRoom) {
		this.livingRoom = livingRoom;
	}
	public boolean isResturant() {
		return resturant;
	}
	public void setResturant(boolean resturant) {
		this.resturant = resturant;
	}
	public boolean isDining() {
		return dining;
	}
	public void setDining(boolean dining) {
		this.dining = dining;
	}
	public boolean isCctv() {
		return cctv;
	}
	public void setCctv(boolean cctv) {
		this.cctv = cctv;
	}
	public boolean isComplementryBreakfast() {
		return complementryBreakfast;
	}
	public void setComplementryBreakfast(boolean complementryBreakfast) {
		this.complementryBreakfast = complementryBreakfast;
	}
	public void setService24_7(boolean service24_7) {
		this.service24_7 = service24_7;
	}
	
	////
	
    public String getDiscountPrint() {
    	return discountInPercentage? discountValue+" %" : discountValue+"";
    }
    
    public double getDiscountAmount() {
    	return discountInPercentage? (price * (discountValue/100)) : discountValue;
    }
    
    public double getDiscountPercentage() {
    	return discountInPercentage? discountValue : (discountValue/price)*100;
    }
    
    public double getDiscountedPrice() {
    	return price - getDiscountAmount();
    }
    
    public List<FabRoom> getFabRoom() {
		return fabRoom;
	}

	public void setFabRoom(List<FabRoom> fabRoom) {
		this.fabRoom = fabRoom;
	}

	public List<RoomImage> getImages() {
		return images;
	}

	public void setImages(List<RoomImage> images) {
		this.images = images;
	}
	
	public int getHotelId() {
		return hotel.getId();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Room other = (Room) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
    
	

}