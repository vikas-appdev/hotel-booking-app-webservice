package com.sdigitizers.hotel.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sdigitizers.hotel.model.AppConfig;

public interface AppConfigRepository extends JpaRepository<AppConfig, Integer> {
	
	Optional<AppConfig> findByType(String type);

}
