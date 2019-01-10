package com.sdigitizers.hotel.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sdigitizers.hotel.model.PromoCode;
import com.sdigitizers.hotel.model.User;

public interface PromoCodeRepository extends JpaRepository<PromoCode, Integer> {
	PromoCode findByCode(String code);

}
