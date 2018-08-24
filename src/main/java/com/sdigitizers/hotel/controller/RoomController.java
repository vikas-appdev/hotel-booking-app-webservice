package com.sdigitizers.hotel.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.sdigitizers.hotel.HotelNotFoundException;
import com.sdigitizers.hotel.model.Hotel;
import com.sdigitizers.hotel.model.Room;
import com.sdigitizers.hotel.repository.HotelRepository;
import com.sdigitizers.hotel.repository.RoomRepository;

@RestController
public class RoomController {
	
	@Autowired
	private RoomRepository roomRepository;
	
	@Autowired
	private HotelRepository hotelRepository;
	
	@GetMapping("rooms")
	public List<Room> retriveAllRoom(){
		return roomRepository.findAll();
	}
	
	@PostMapping("rooms")
	public ResponseEntity<Object> createHotel(@RequestBody Room room) {
		Room save = roomRepository.save(room);
		
		URI location = ServletUriComponentsBuilder
		.fromCurrentRequest()
		.path("/{id}")
		.buildAndExpand(save.getId()).toUri();
		
		return ResponseEntity.created(location).build();
		
	}
	
	@PostMapping("/hotel/{id}/rooms")
	public ResponseEntity<Object> createRoom(@PathVariable int id, @RequestBody Room room) {
		
		Optional<Hotel> hotelOptional = hotelRepository.findById(id);
		
		if(!hotelOptional.isPresent()) {
			throw new HotelNotFoundException("id- "+id);
		}
		
		Hotel hotel = hotelOptional.get();
		
		
		room.setHotel(hotel);
		
		roomRepository.save(room);
		
		URI location = ServletUriComponentsBuilder
		.fromCurrentRequest()
		.path("/{id}")
		.buildAndExpand(room.getId()).toUri();
		
		return ResponseEntity.created(location).build();
		
	}

}