package com.sdigitizers.hotel.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sdigitizers.hotel.model.Admin;

public interface AdminRepository extends JpaRepository<Admin, Integer> {
	

}
