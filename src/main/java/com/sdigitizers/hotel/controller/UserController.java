package com.sdigitizers.hotel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.sdigitizers.hotel.model.User;
import com.sdigitizers.hotel.repository.UserRepository;

@RestController
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/users/{id}")
	public User retriveUser(@PathVariable String id) {
		return userRepository.findByEmail(id);
		
	}
	
	@GetMapping("/users")
	public List<User> retriveAllUsers(){
		return userRepository.findAll();
		
	}

}
