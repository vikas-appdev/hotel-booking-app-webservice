package com.sdigitizers.hotel.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sdigitizers.hotel.model.Booking;

public interface BookingRepository extends JpaRepository<Booking, Integer> {
	
	List<Booking> findByUserId(int userId);
	Booking findByUserIdAndUptoTime(Integer id, LocalDateTime uptoTime);
	List<Booking> findByUptoTimeLessThanEqualAndUserId(LocalDateTime uptoTime, Integer id);
	List<Booking> findByFromTimeGreaterThanEqualAndUserId(LocalDateTime fromTime, Integer id);

}
