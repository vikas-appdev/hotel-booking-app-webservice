package com.sdigitizers.hotel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sdigitizers.hotel.model.PromoCode;
import com.sdigitizers.hotel.model.PromocodeTransaction;
import com.sdigitizers.hotel.model.User;

public interface PromoCodeTransactionRepository extends JpaRepository<PromocodeTransaction, Integer> {
	
	List<PromocodeTransaction> findByUserAndPromoCode(User user, PromoCode code);

}
