package com.sdigitizers.hotel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sdigitizers.hotel.model.BookingReview;
import com.sdigitizers.hotel.repository.BookingReviewRepository;

@RestController
public class BookingReviewController {
	
	@Autowired
	private BookingReviewRepository bookingReviewRepository;
	
	@PostMapping("/bookingreview")
	public void saveBookingReview(@RequestBody BookingReview bookingReview) {
		
		bookingReviewRepository.save(bookingReview);
		
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
