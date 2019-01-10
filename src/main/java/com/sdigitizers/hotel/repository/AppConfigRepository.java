package com.sdigitizers.hotel.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sdigitizers.hotel.model.AppConfig;

public interface AppConfigRepository extends JpaRepository<AppConfig, Integer> {

}
