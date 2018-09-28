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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.sdigitizers.hotel.UserNotFoundException;
import com.sdigitizers.hotel.codec.BookingStatus;
import com.sdigitizers.hotel.model.Booking;
import com.sdigitizers.hotel.model.Hotel;
import com.sdigitizers.hotel.model.Transaction;
import com.sdigitizers.hotel.model.User;
import com.sdigitizers.hotel.repository.UserRepository;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

@RestController
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
	
	
	@GetMapping("/users/{email}")
	public Optional<User> retriveUserByEmail(@PathVariable String email) {
		Optional<User> user = userRepository.findByEmail(email);
		if(!user.isPresent())
			throw new UserNotFoundException("id- "+email);
		
		return user;
		
	}
	
	@GetMapping("/users/{id}")
	public Optional<User> retriveUserById(@PathVariable int id) {
		Optional<User> user = userRepository.findById(id);
		if(!user.isPresent())
			throw new UserNotFoundException("id- "+id);
		
		return user;
		
	}
	
	@PostMapping("login")
	public User retriveUserByEmailAndPassword(@RequestBody String email, @RequestBody String pass) {
		return userRepository.findByEmailAndPassword(email, pass);
	}
	
	
	@GetMapping("/users")
	public List<User> retriveAllUsers(){
		return userRepository.findAll();
		
	}
	
	@PostMapping("/users")
	public User createUser(@RequestBody User user) {
		return userRepository.save(user);
		
		/*URI location = ServletUriComponentsBuilder
		.fromCurrentRequest()
		.path("/{id}")
		.buildAndExpand(savedUser.getId()).toUri();
		
		return ResponseEntity.created(location).build();*/
		
	}
	
	@PutMapping("/users/{id}")
	public User updateUser(@RequestBody User user,@PathVariable String id) {
		
		Optional<User> userOptional = userRepository.findByEmail(id);
		if(!userOptional.isPresent())
			throw new UserNotFoundException("id- "+id);

		User user2 = userOptional.get();
		
		user.setId(user2.getId());
		
		
		
		
		return userRepository.save(user);

		
		
	}
	

}
