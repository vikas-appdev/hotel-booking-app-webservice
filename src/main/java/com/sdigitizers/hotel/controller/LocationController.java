package com.sdigitizers.hotel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.sdigitizers.hotel.model.Hotel;
import com.sdigitizers.hotel.model.Location;
import com.sdigitizers.hotel.repository.LocationRepository;

@RestController
public class LocationController {
	
	@Autowired
	private LocationRepository locationRepository;
	
	@GetMapping("hotels/{lat}/{lung}")
	public List<Hotel> retriveHotelbyLocation(@PathVariable double lat, @PathVariable double lung) {
		
		
		
		return locationRepository.findHotelByLocation(lat, lung);
		
	}
	
	@GetMapping("locations")
	public List<Location> retriveAllLocations() {
		return locationRepository.findAll();
	}
	
	

}
