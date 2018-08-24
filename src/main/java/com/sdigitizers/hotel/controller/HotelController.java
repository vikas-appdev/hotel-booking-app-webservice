package com.sdigitizers.hotel.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.sdigitizers.hotel.model.Hotel;
import com.sdigitizers.hotel.repository.HotelRepository;

@RestController
public class HotelController {

	@Autowired
	private HotelRepository hotelRepository;

	@GetMapping("hotels")
	public List<Hotel> retriveAllHotels() {
		return hotelRepository.findAll();
	}
	
	@PostMapping("hotels")
	public ResponseEntity<Object> createHotel(@RequestBody Hotel hotel) {
		Hotel savedHotel = hotelRepository.save(hotel);
		
		URI location = ServletUriComponentsBuilder
		.fromCurrentRequest()
		.path("/{id}")
		.buildAndExpand(savedHotel.getId()).toUri();
		
		return ResponseEntity.created(location).build();
		
	}

}
