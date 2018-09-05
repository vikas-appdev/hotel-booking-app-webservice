package com.sdigitizers.hotel.controller;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
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
import com.sdigitizers.hotel.repository.LocationRepository;
import com.sdigitizers.hotel.repository.RoomRepository;

@RestController
public class LocationController {
	
	@Autowired
	private LocationRepository locationRepository;
	
	@GetMapping("hotels/{lat}/{lung}")
	public List<Hotel> retriveHotelbyLocation(@PathVariable double lat, @PathVariable double lung) {
		
		
		
		return locationRepository.findHotelByLocation(lat, lung);
		
	}
	
	
	

}
