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
import com.sdigitizers.hotel.model.FabRoom;
import com.sdigitizers.hotel.model.User;
import com.sdigitizers.hotel.repository.FabRoomRepository;
import com.sdigitizers.hotel.repository.UserRepository;

@RestController
public class FabRoomController {
	
	@Autowired
	private FabRoomRepository fabRoomRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	
	
	@GetMapping("fabrooms/{email}")
	public List<FabRoom> retriveUser(@PathVariable String email) {
		
		Optional<User> userOptional = userRepository.findByEmail(email);
		
		if(!userOptional.isPresent()) {
			throw new UserNotFoundException("id- "+email);
		}
		
		User user = userOptional.get();
		
		
		return fabRoomRepository.findByUserId(user.getId());
		
		
	}
	
	@GetMapping("/fabrooms")
	public List<FabRoom> retriveAllUsers(){
		return fabRoomRepository.findAll();
		
	}
	
	@PostMapping("/fabrooms")
	public ResponseEntity<Object> createUser(@RequestBody FabRoom fabroom) {
		FabRoom savedRoom = fabRoomRepository.save(fabroom);
		
		
		URI location = ServletUriComponentsBuilder
		.fromCurrentRequest()
		.path("/{id}")
		.buildAndExpand(savedRoom.getId()).toUri();
		
		return ResponseEntity.created(location).build();
		
	}
	
	
	
	

}
