package com.sdigitizers.hotel.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sdigitizers.hotel.UserNotFoundException;
import com.sdigitizers.hotel.exception.NotFounWalaException;
import com.sdigitizers.hotel.model.Booking;
import com.sdigitizers.hotel.model.Hotel;
import com.sdigitizers.hotel.model.Room;
import com.sdigitizers.hotel.model.User;
import com.sdigitizers.hotel.repository.BookingRepository;
import com.sdigitizers.hotel.repository.UserRepository;

@RestController
public class BookingController {
	
	@Autowired
	private BookingRepository bookingRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	
	@GetMapping("booking")
	public List<Booking> retriveAllBooking() {
		return bookingRepository.findAll();
	}
	
	@GetMapping("booking/{email}")
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
	
	@GetMapping("booking/{email}/{uptoTime}")
	public List<Booking> retriveBookingByUserAndUpto(@PathVariable String email, @PathVariable @DateTimeFormat(iso=ISO.DATE_TIME) LocalDateTime uptoTime) {
		
		Optional<User> userOptional = userRepository.findByEmail(email);
		
		if(!userOptional.isPresent()) {
			throw new UserNotFoundException("id- "+email);
		}
		
		User user = userOptional.get();
		
		
		return bookingRepository.findByFromTimeLessThanEqualAndUserId(uptoTime, user.getId());
		
	}
	
	@GetMapping("booking/{email}/from/{from}")
	public List<Booking> retriveBookingByUserAndFrom(@PathVariable String email, @PathVariable @DateTimeFormat(iso=ISO.DATE_TIME) LocalDateTime from) {
		
		Optional<User> userOptional = userRepository.findByEmail(email);
		
		if(!userOptional.isPresent()) {
			throw new UserNotFoundException("id- "+email);
		}
		
		User user = userOptional.get();
		
		
		return bookingRepository.findByFromTimeGreaterThanEqualAndUserId(from, user.getId());
		
	}
	
	
	
	@PostMapping("booking")
	public Booking createBooking(@RequestBody Booking booking) {
		//int hotelId = booking.getRoom().getHotelId();
		
		//booking.setHotelId(hotelId);
		
		return bookingRepository.save(booking);
		
		/*URI location = ServletUriComponentsBuilder
		.fromCurrentRequest()
		.path("/{id}")
		.buildAndExpand(savedBooking.getId()).toUri();
		
		return ResponseEntity.created(location).build();*/
		
	}
	
	@PutMapping("booking/{id}")
	public Booking updateBooking(@RequestBody Booking booking, @PathVariable int id) {
		
		Optional<Booking> optional = bookingRepository.findById(id);
		
		if(!optional.isPresent()) {
			throw new NotFounWalaException("Booking not found" +id);
		}
		
		
		booking.setId(id);
		
		return bookingRepository.save(booking);
		
		
	}
	
	
	

}
