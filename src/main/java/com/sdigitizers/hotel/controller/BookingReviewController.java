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
import com.sdigitizers.hotel.exception.NotFounWalaException;
import com.sdigitizers.hotel.model.Booking;
import com.sdigitizers.hotel.model.BookingReview;
import com.sdigitizers.hotel.model.Hotel;
import com.sdigitizers.hotel.repository.BookingRepository;
import com.sdigitizers.hotel.repository.BookingReviewRepository;

@RestController
public class BookingReviewController {
	
	@Autowired
	private BookingReviewRepository bookingReviewRepository;
	
	@Autowired
	private BookingRepository bookingRepository;
	
	@PostMapping("/{id}/bookingreview")
	public ResponseEntity<Object> saveBookingReview(@PathVariable int id, @RequestBody BookingReview bookingReview) {
		
		Optional<Booking> bookingOptional = bookingRepository.findById(id);
		
		if(!bookingOptional.isPresent()) {
			throw new NotFounWalaException("id- "+id);
		}
		
		Booking booking = bookingOptional.get();
		
		
		bookingReview.setBooking(booking);
		
		bookingReviewRepository.save(bookingReview);
		
		URI location = ServletUriComponentsBuilder
		.fromCurrentRequest()
		.path("/{id}")
		.buildAndExpand(bookingReview.getId()).toUri();
		
		return ResponseEntity.created(location).build();
		
	}
	
	@GetMapping("/bookingreview/byhotel/{hotelId}")
	public List<BookingReview> getBookingReviewByHotel(@PathVariable int hotelId) {
		
		return bookingReviewRepository.findByHotel(hotelId);
		
	}
	
	@GetMapping("/bookingreview/byroom/{roomId}")
	public List<BookingReview> getBookingReviewByRoom(@PathVariable int roomId){
		return bookingReviewRepository.findByRoom(roomId);
	}
	
	public List<BookingReview> getBookingReviewByUser(@PathVariable int userId){
		return bookingReviewRepository.findByUser(userId);
	}
	
	

}
