package com.sdigitizers.hotel.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.sdigitizers.hotel.UserNotFoundException;
import com.sdigitizers.hotel.model.Admin;
import com.sdigitizers.hotel.model.User;
import com.sdigitizers.hotel.repository.AdminRepository;

@Repository
public class AdminController {
	
	@Autowired
	private AdminRepository adminRepository;

	@GetMapping("admins/{email}/{pass}")
	public Optional<Admin> retriveUserByEmail(@PathVariable String email, @PathVariable String password) {
		Optional<Admin> user = adminRepository.findByEmailAndPassword(email, password);
		if(!user.isPresent())
			throw new UserNotFoundException("id- "+email);
		
		return user;
		
	}
}
