package com.sdigitizers.hotel.model;

import javax.persistence.GeneratedValue;
import javax.persistence.OneToMany;

public class FabRoom {

	@GeneratedValue
	private int id;
	@OneToMany
	private Room room;
	@OneToMany
	private User user;
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
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	
}
