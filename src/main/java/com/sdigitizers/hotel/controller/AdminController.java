package com.sdigitizers.hotel.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.sdigitizers.hotel.AdminNotFoundException;
import com.sdigitizers.hotel.UserNotFoundException;
import com.sdigitizers.hotel.model.Admin;
import com.sdigitizers.hotel.model.Hotel;
import com.sdigitizers.hotel.model.User;
import com.sdigitizers.hotel.repository.AdminRepository;

@Repository
public class AdminController {
	
	@Autowired
	private AdminRepository adminRepository;

	@GetMapping("admin")
	public Admin retriveAdmin() {
		Admin one = adminRepository.getOne(1);
		if(one.getEmail()==null) {
			new AdminNotFoundException("Not FOund");
		}
		return one;
	}
	
	@GetMapping("superadmins")
	public List<Admin> retriveAllHotels() {
		return adminRepository.findAll();
	}
}
