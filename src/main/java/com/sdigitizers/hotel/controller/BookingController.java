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

import com.sdigitizers.hotel.UserNotFoundException;
import com.sdigitizers.hotel.model.Booking;
import com.sdigitizers.hotel.model.User;
import com.sdigitizers.hotel.repository.BookingRepository;
import com.sdigitizers.hotel.repository.UserRepository;

@RestController
public class BookingController {
	
	@Autowired
	private BookingRepository bookingRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	
	@GetMapping("room/bookings")
	public List<Booking> retriveAllBooking() {
		return bookingRepository.findAll();
	}
	
	@GetMapping("room/bookings/{email}")
	public Booking retriveBookingByUser(@PathVariable String email) {
		
		Optional<User> userOptional = userRepository.findByEmail(email);
		
		if(!userOptional.isPresent()) {
			throw new UserNotFoundException("id- "+email);
		}
		
		User user = userOptional.get();
		
		return bookingRepository.findByUserId(user.getId());
		
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
