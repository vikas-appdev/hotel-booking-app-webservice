package com.sdigitizers.hotel.repository;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sdigitizers.hotel.model.Booking;
import com.sdigitizers.hotel.model.FabRoom;

public interface BookingRepository extends JpaRepository<Booking, Integer> {
	
	Booking findByUserId(int userId);
	Booking findByUserIdAndUptoTime(Integer id, LocalDateTime uptoTime);
	Booking findByUptoTimeLessThanEqualAndUserId(LocalDateTime uptoTime, Integer id);
	Booking findByFromTimeGreaterThanEqualAndUserId(LocalDateTime fromTime, Integer id);

}
