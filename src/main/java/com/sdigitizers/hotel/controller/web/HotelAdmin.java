package com.sdigitizers.hotel.controller.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sdigitizers.hotel.HotelNotFoundException;
import com.sdigitizers.hotel.codec.BookingStatus;
import com.sdigitizers.hotel.codec.PaymentStatus;
import com.sdigitizers.hotel.codec.RoomCategory;
import com.sdigitizers.hotel.exception.NotFounWalaException;
import com.sdigitizers.hotel.model.Address;
import com.sdigitizers.hotel.model.Booking;
import com.sdigitizers.hotel.model.Hotel;
import com.sdigitizers.hotel.model.Room;
import com.sdigitizers.hotel.repository.BookingRepository;
import com.sdigitizers.hotel.repository.HotelRepository;
import com.sdigitizers.hotel.repository.RoomRepository;

@Controller
public class HotelAdmin {
	
	@Autowired
	private HotelRepository hotelRepository;
	
	@Autowired
	private RoomRepository roomRepository;
	
	@Autowired
	private BookingRepository bookingRepository;
	
	
	@GetMapping("/signin.html")
	public String signin(Model model) {
		return "signin";
	}
	
	@PostMapping("/loginv.html")
	public String login(@RequestParam String email,
			@RequestParam String password, 
			Model model,
			HttpServletRequest request,
			HttpSession session) {
		List<Hotel> all = hotelRepository.findAll();
		for (Hotel hotel : all) {
			if (hotel.getEmail().equals(email)&&hotel.getPassword().equals(password)) {
				session = request.getSession(true);
				session.setAttribute("email", hotel.getEmail());
				session.setAttribute("name", hotel.getContactPerson());
				session.setAttribute("id", hotel.getId());
				return "redirect:hotelv.html";
			}
		}
		model.addAttribute("invalid", "Please check your user name or password");
		
		return "signin";
	}
	
	@GetMapping("/p2r-admin")
	public String index(Model model, HttpServletRequest request, HttpSession session) {
		session = request.getSession(false);
		String email = (String) session.getAttribute("email");
		if (email!=null) {
			return "redirect:hotelv.html";
		}
		return "signin";
	}
	
	@GetMapping("/hotelv.html")
	public String index(HttpServletRequest request, HttpSession session, Model model) {
		session = request.getSession(false);
		String email = (String) session.getAttribute("email");
		String name =  (String) session.getAttribute("name");
		
		if (email!=null) {
			model.addAttribute("hotels", hotelRepository.findAll());
			model.addAttribute("email", email);
			model.addAttribute("name", name);
			return "hotel";
		}
		return "signin";
	}
	
	@GetMapping("/addhotel")
	public String addHotel(HttpServletRequest request, HttpSession session, Model model) {
		session = request.getSession(false);
		String email = (String) session.getAttribute("email");
		String name =  (String) session.getAttribute("name");
		
		if (email!=null) {
			model.addAttribute("hotels", hotelRepository.findAll());
			model.addAttribute("email", email);
			model.addAttribute("name", name);
			return "addhotel";
		}
		return "signin";
		
	}
	
	@PostMapping("/posthotel")
	public String postHotel(@RequestParam String name, @RequestParam String email, @RequestParam String locality,
			@RequestParam String countryCode, @RequestParam String phone1, @RequestParam String phone2, 
			@RequestParam String street, @RequestParam String city, @RequestParam String state, @RequestParam String country,
			@RequestParam String pincode, @RequestParam String latitude, @RequestParam String longitude, 
			@RequestParam String contactPerson, @RequestParam String password, @RequestParam String description,
			@RequestParam String terms, Model model) {

		Hotel hotel = new Hotel();
		
		Address add = new Address();
		add.setCity(city);
		add.setCountry(country);
		add.setLatitude(latitude);
		add.setLongitude(longitude);
		add.setPincode(Integer.parseInt(pincode));
		add.setState(state);
		add.setStreet(street);
		
		hotel.setAddress(add);
		hotel.setContactPerson(contactPerson);
		hotel.setCountryCode(countryCode);
		hotel.setDescription(description);
		hotel.setEmail(email);
		hotel.setLocality(locality);
		hotel.setName(name);
		hotel.setPassword(password);
		hotel.setPhone1(Long.parseLong(phone1));
		hotel.setPhone2(Long.parseLong(phone2));
		hotel.setRating(5.0);
		hotel.setStatus(true);
		hotel.setTerms(terms);
		
		Hotel save = hotelRepository.save(hotel);
		
		model.addAttribute("success", save);
		
		return "addhotel";
	}
	
	@GetMapping("/addroom")
	public String addRoom(HttpServletRequest request, HttpSession session, Model model) {
		session = request.getSession(false);
		String email = (String) session.getAttribute("email");
		String name =  (String) session.getAttribute("name");
		
		if (email!=null) {
			model.addAttribute("hotels", hotelRepository.findAll());
			model.addAttribute("email", email);
			model.addAttribute("name", name);
			return "addroom";
		}
		return "signin";
		
	}
	
	@PostMapping("/postroom")
	public String postRoom(@RequestParam String hotelid, @RequestParam String name, @RequestParam String category, @RequestParam String price, @RequestParam String dprecent,
			@RequestParam String dvalue, @RequestParam String persons, @RequestParam(required=false) String ac, @RequestParam(required=false) String cctv, @RequestParam(required=false) String cbreakfast,
			@RequestParam(required=false) String callowed, @RequestParam(required=false) String dining, @RequestParam(required=false) String fallowed, @RequestParam(required=false) String geyser, @RequestParam(required=false) String heater,
			@RequestParam(required=false) String livingroom, @RequestParam(required=false) String minifridge, @RequestParam(required=false) String parking, @RequestParam(required=false) String resturant, @RequestParam(required=false) String service24x7,
			@RequestParam(required=false) String tv, @RequestParam(required=false) String wifi, @RequestParam(required=false) String active) {
		
		Room room = new Room();
		
		Optional<Hotel> hotelOptional = hotelRepository.findById(Integer.parseInt(hotelid));
		
		if (!hotelOptional.isPresent()) {
			throw new HotelNotFoundException("+id"+ hotelid);
		}
		
		Hotel hotel = hotelOptional.get();
		
		room.setHotel(hotel);
		
		
		if (ac.equals("on")) {
			room.setAc(true);
		}else {
			room.setAc(false);
		}
		
		if (active.equals("on")) {
			room.setActive(true);
		}else {
			room.setActive(false);
		}
		
		room.setCategory(RoomCategory.valueOf(category));
		
		if (cctv.equals("on")) {
			room.setCctv(true);
		}else {
			room.setCctv(false);
		}
		
		if (cbreakfast.equals("on")) {
			room.setComplementaryBreakfast(true);
		}else {
			room.setComplementaryBreakfast(false);
		}
		
		if (callowed.equals("on")) {
			room.setCoupleAllowed(true);
		}else {
			room.setCoupleAllowed(false);
		}
		
		if (dprecent.equals("on")) {
			room.setDicountInPercentage(true);
		}else {
			room.setDicountInPercentage(false);
		}
		
		
		if (dining.equals("on")) {
			room.setDining(true);
		}else {
			room.setDining(false);
		}
		
		room.setDiscountValue(Double.parseDouble(dvalue));
		
		if (fallowed.equals("on")) {
			room.setFamilyAllowed(true);
		}else {
			room.setFamilyAllowed(false);
		}
		
		if (geyser.equals("on")) {
			room.setGeyser(true);
		}else {
			room.setGeyser(false);
		}
		
		if (heater.equals("on")) {
			room.setHeater(true);
		}else {
			room.setHeater(false);
		}
		
		if(livingroom.equals("on")) {
			room.setLivingRoom(true);
		}else {
			room.setLivingRoom(false);
		}
		
		
		if (minifridge.equals("on")) {
			room.setMiniFridge(true);
		}else {
			room.setMiniFridge(false);
		}
		
		
		
		room.setName(name);
		room.setNoOfPersons(Integer.parseInt(persons));
		
		if (parking.equals("on")) {
			room.setParking(true);
		}else {
			room.setParking(false);
		}
		
		room.setPrice(Double.parseDouble(price));
		
		if (resturant.equals("on")) {
			room.setRestaurant(true);
		}else {
			room.setRestaurant(false);
		}
		
		if (service24x7.equals("on")) {
			room.setService24x7(true);
		}else {
			room.setService24x7(false);
		}
		
		if (tv.equals("on")) {
			room.setTv(true);
		}else {
			room.setTv(false);
		}
		
		if (wifi.equals("on")) {
			room.setWifi(true);
		}else {
			room.setWifi(false);
		}
		
		
		roomRepository.save(room);
		
		return "addroom";
	}
	
	@GetMapping("/allbooking.html")
	public String showAllBookingForHotel(Model model, HttpServletRequest request, HttpSession session) {
		
		session = request.getSession(false);
		if (session.getAttribute("email")==null) {
			return "signin";
		}
		String email = (String) session.getAttribute("email");
		String name =  (String) session.getAttribute("name");
		
		int hotelid = (int) session.getAttribute("id");
		Optional<Hotel> optional = hotelRepository.findById(hotelid);
		Hotel hotel = optional.get();
		List<Room> rooms = hotel.getRooms();
		
		List<Booking> findAll = bookingRepository.findAll();
		List<Booking> bookings = new ArrayList<>();
		
		for (Room room : rooms) {
			for (Booking booking : findAll) {
				if (booking.getRoom().getId()==room.getId()) {
					bookings.add(booking);
				}
			}
		}
		
		
		
		model.addAttribute("bookings", bookings);
		model.addAttribute("email", email);
		model.addAttribute("name", name);
		
		if (email!=null) {
			return "showallbooking";
		}
		return "signin";
		
	}
	@GetMapping("/paid/{id}/")
	public String paid(@PathVariable int id) {
		Optional<Booking> optional = bookingRepository.findById(id);
		if (!optional.isPresent()) {
			throw new NotFounWalaException("Booking Not Found with id"+ id);
		}
		Booking booking = optional.get();
		booking.setPaymentStatus(PaymentStatus.PAID);
		bookingRepository.save(booking);
		
		return "redirect:../../allbooking.html";
	}
	
	@GetMapping("/status/{id}/{status}")
	public String status(@PathVariable int id, @PathVariable String status) {
		Optional<Booking> optional = bookingRepository.findById(id);
		if (!optional.isPresent()) {
			throw new NotFounWalaException("Booking Not Found with id"+ id);
		}
		Booking booking = optional.get();
		booking.setStatus(BookingStatus.valueOf(status));
		bookingRepository.save(booking);
		
		return "redirect:../../allbooking.html";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:signin.html";
	}

}
