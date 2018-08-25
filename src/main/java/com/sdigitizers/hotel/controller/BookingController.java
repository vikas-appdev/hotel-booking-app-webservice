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

import com.sdigitizers.hotel.model.Booking;
import com.sdigitizers.hotel.model.Hotel;
import com.sdigitizers.hotel.repository.BookingRepository;

@RestController
public class BookingController {
	
	@Autowired
	private BookingRepository bookingRepository;
	
	@GetMapping("room/bookings")
	public List<Booking> retriveAllBooking() {
		return bookingRepository.findAll();
	}
	
	@PostMapping("room/bookings")
	public ResponseEntity<Object> createBooking(@RequestBody Booking booking) {
		Booking savedBooking = bookingRepository.save(booking);
		
		URI location = ServletUriComponentsBuilder
		.fromCurrentRequest()
		.path("/{id}")
		.buildAndExpand(savedBooking.getId()).toUri();
		
		return ResponseEntity.created(location).build();
		
	}

}
