package com.sdigitizers.hotel.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sdigitizers.hotel.model.Promotion;

public interface PromotionRepository extends JpaRepository<Promotion, Integer> {
	
	

}
