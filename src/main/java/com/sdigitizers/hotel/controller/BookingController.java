package com.sdigitizers.hotel.controller;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
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
	public List<Booking> retriveBookingByUser(@PathVariable String email) {
		
		Optional<User> userOptional = userRepository.findByEmail(email);
		
		if(!userOptional.isPresent()) {
			throw new UserNotFoundException("id- "+email);
		}
		
		User user = userOptional.get();
		
		return bookingRepository.findByUserId(user.getId());
		
	}
	
	
	@GetMapping("booking/validate/{fromDateTime}/{uptoDateTime}/{roomId}")
	public boolean validateBookingForRoom(@PathVariable @DateTimeFormat(iso=ISO.DATE_TIME) LocalDateTime fromDateTime,
			@PathVariable @DateTimeFormat(iso=ISO.DATE_TIME) LocalDateTime uptoDateTime, @PathVariable int roomId) {
		List<Booking> bookingsFound = bookingRepository.findBookingsForRoom(roomId, fromDateTime, uptoDateTime);
		return bookingsFound==null?true:(bookingsFound.isEmpty());
	}
	
	@GetMapping("room/bookings/{email}/{uptoTime}")
	public List<Booking> retriveBookingByUserAndUpto(@PathVariable String email, @PathVariable @DateTimeFormat(iso=ISO.DATE_TIME) LocalDateTime uptoTime) {
		
		Optional<User> userOptional = userRepository.findByEmail(email);
		
		if(!userOptional.isPresent()) {
			throw new UserNotFoundException("id- "+email);
		}
		
		User user = userOptional.get();
		
		
		return bookingRepository.findByFromTimeLessThanEqualAndUserId(uptoTime, user.getId());
		
	}
	
	@GetMapping("room/bookings/{email}/from/{from}")
	public List<Booking> retriveBookingByUserAndFrom(@PathVariable String email, @PathVariable @DateTimeFormat(iso=ISO.DATE_TIME) LocalDateTime from) {
		
		Optional<User> userOptional = userRepository.findByEmail(email);
		
		if(!userOptional.isPresent()) {
			throw new UserNotFoundException("id- "+email);
		}
		
		User user = userOptional.get();
		
		
		return bookingRepository.findByFromTimeGreaterThanEqualAndUserId(from, user.getId());
		
	}
	
	
	
	@PostMapping("room/bookings")
	public Booking createBooking(@RequestBody Booking booking) {
		return bookingRepository.save(booking);
		
		/*URI location = ServletUriComponentsBuilder
		.fromCurrentRequest()
		.path("/{id}")
		.buildAndExpand(savedBooking.getId()).toUri();
		
		return ResponseEntity.created(location).build();*/
		
	}

}
