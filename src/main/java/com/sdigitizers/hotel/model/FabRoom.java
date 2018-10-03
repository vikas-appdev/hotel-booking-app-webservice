package com.sdigitizers.hotel.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;

@Entity
@IdClass(FabRoomCompositeKey.class)
public class FabRoom implements Serializable {

	@GeneratedValue
	@Id
	private Integer id;
	
	@ManyToOne
	private Room room;
	@Id
	@ManyToOne
	private User user;
	
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Room getRoom() {
		return room;
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
	
	
	
	
	
		
	
}
