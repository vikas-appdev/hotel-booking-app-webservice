package com.sdigitizers.hotel.controller;

import java.time.LocalDate;
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
import com.sdigitizers.hotel.codec.BookingStatus;
import com.sdigitizers.hotel.codec.FeedbackStatus;
import com.sdigitizers.hotel.codec.PaymentStatus;
import com.sdigitizers.hotel.codec.TransactionType;
import com.sdigitizers.hotel.exception.NotFounWalaException;
import com.sdigitizers.hotel.model.Booking;
import com.sdigitizers.hotel.model.BookingReview;
import com.sdigitizers.hotel.model.CustomerWalletTxn;
import com.sdigitizers.hotel.model.Hotel;
import com.sdigitizers.hotel.model.Room;
import com.sdigitizers.hotel.model.User;
import com.sdigitizers.hotel.repository.BookingRepository;
import com.sdigitizers.hotel.repository.CustomerWalletTxnRepository;
import com.sdigitizers.hotel.repository.UserRepository;

@RestController
public class BookingController {
	
	@Autowired
	private BookingRepository bookingRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CustomerWalletTxnRepository customerWalletTxn;
	
	
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
		Booking save = bookingRepository.save(booking);
		
		//booking.setHotelId(hotelId);
		double walletMoneyUsed = booking.getWalletMoneyUsed();
		User user = booking.getUser();
		
		
		if (walletMoneyUsed!=0) {
			CustomerWalletTxn txn = new CustomerWalletTxn();
			txn.setAmount(walletMoneyUsed);
			txn.setDate(LocalDate.now());
			txn.setRemarks("Room Booking");
			txn.setTxnRef(""+save.getId());
			txn.setTxnType(TransactionType.DEBIT);
			double walletBalance = user.getWalletBalance()-walletMoneyUsed;
			user.setWalletBalance(walletBalance);
			customerWalletTxn.save(txn);
			userRepository.save(user);
		}
		
		return save;
		
		/*URI location = ServletUriComponentsBuilder
		.fromCurrentRequest()
		.path("/{id}")
		.buildAndExpand(savedBooking.getId()).toUri();
		
		return ResponseEntity.created(location).build();*/
		
	}
	
	@PostMapping("booking/offline/{from}/{upto}")
	public void createOfflineBooking(@PathVariable @DateTimeFormat(iso=ISO.DATE_TIME) LocalDateTime from,
			@PathVariable @DateTimeFormat(iso=ISO.DATE_TIME) LocalDateTime upto,
			@RequestBody Room room) {
		
		Booking booking = new Booking();
		
		Optional<User> userOptional = userRepository.findById(404);
		User user = userOptional.get();
		
		
		booking.setBookedFor("Offline User");
		booking.setBusinessTrip(false);
		booking.setCheckinTime(from);//Take from user
		booking.setCheckoutTime(upto);//take from user
		booking.setCountryCode("91");
		booking.setDiscountAmount(0.0);
		booking.setEmail(user.getEmail());
		booking.setFeedbackStatus(FeedbackStatus.NA);
		booking.setFromTime(LocalDateTime.now());//take from user
		booking.setGSTIN("NA");
		booking.setPaymentStatus(PaymentStatus.PAID);
		booking.setPhone(91L);
		booking.setPrice(0.0);
		booking.setPromoCodeAmountUsed(0.0);
		booking.setRoom(room);
		booking.setSpecialNote("offline booking");
		booking.setStatus(BookingStatus.ACKNOWLEDGED);
		booking.setTime(LocalDateTime.now());
		booking.setUptoTime(upto);//from user
		booking.setUser(user);
		
		
		//int hotelId = booking.getRoom().getHotelId();
		Booking save = bookingRepository.save(booking);
		
		
		
		
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
	
	@GetMapping("booking/hotel/{hotelid}/p")
	public List<Booking> getPastBookingByHotelId(@PathVariable int hotelid){
		return bookingRepository.findPastBookingsForHotel(hotelid, LocalDateTime.now());
	}
	
	@GetMapping("booking/hotel/{hotelid}/c")
	public List<Booking> getCommingBookingByHotelId(@PathVariable int hotelid){
		return bookingRepository.findUpcomingBookingsForHotel(hotelid, LocalDateTime.now());
	}
	
	@GetMapping("booking/hotel/{hotelid}/all")
	public List<Booking> getAllBookingByHotelId(@PathVariable int hotelid){
		return bookingRepository.findAllBookingsForHotel(hotelid);
	}
	
	@GetMapping("booking/hotel/{hotelid}/all/{date}")
	public List<Booking> getAllBookingByHotelIdForSingleDate(@PathVariable int hotelid, @PathVariable @DateTimeFormat(iso=ISO.DATE_TIME) LocalDateTime date){
		return bookingRepository.findUpcomingBookingsForHotelBySingleDate(hotelid, date);
	}
	
	
	

}
