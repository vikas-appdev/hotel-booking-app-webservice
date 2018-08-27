package com.sdigitizers.hotel.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sdigitizers.hotel.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	
	User findByEmail(String email);

}
