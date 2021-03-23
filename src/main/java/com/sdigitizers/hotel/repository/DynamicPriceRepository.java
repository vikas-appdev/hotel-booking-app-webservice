package com.sdigitizers.hotel.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sdigitizers.hotel.model.DynamicPrice;
import com.sdigitizers.hotel.model.Room;

public interface DynamicPriceRepository extends JpaRepository<DynamicPrice, Integer> {

}
