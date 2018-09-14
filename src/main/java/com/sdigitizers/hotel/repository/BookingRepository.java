package com.sdigitizers.hotel.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sdigitizers.hotel.model.Booking;
import com.sdigitizers.hotel.model.FabRoom;

public interface BookingRepository extends JpaRepository<Booking, Integer> {
	
	Booking findByUserId(int userId);

}
