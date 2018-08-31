package com.sdigitizers.hotel.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.sdigitizers.hotel.UserNotFoundException;
import com.sdigitizers.hotel.model.User;
import com.sdigitizers.hotel.repository.UserRepository;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

@RestController
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
	
	
	@GetMapping("/users/{id}")
	public Optional<User> retriveUserByEmail(@PathVariable String id) {
		Optional<User> user = userRepository.findByEmail(id);
		if(!user.isPresent())
			throw new UserNotFoundException("id- "+id);
		
		return user;
		
	}
	
	@GetMapping("users/{email}/{pass}")
	public User retriveUserByEmailAndPassword(@PathVariable String email, @PathVariable String pass) {
		return userRepository.findByEmailAndPassword(email, pass);
	}
	
	
	@GetMapping("/users")
	public List<User> retriveAllUsers(){
		return userRepository.findAll();
		
	}
	
	@PostMapping("/users")
	public ResponseEntity<Object> createUser(@RequestBody User user) {
		User savedUser = userRepository.save(user);
		
		URI location = ServletUriComponentsBuilder
		.fromCurrentRequest()
		.path("/{id}")
		.buildAndExpand(savedUser.getId()).toUri();
		
		return ResponseEntity.created(location).build();
		
	}
	
	

}
