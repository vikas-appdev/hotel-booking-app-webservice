package com.sdigitizers.hotel.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sdigitizers.hotel.model.Booking;

public interface BookingRepository extends JpaRepository<Booking, Integer> {
	
	List<Booking> findByUserId(int userId);
	Booking findByUserIdAndUptoTime(Integer id, LocalDateTime uptoTime);
	List<Booking> findByFromTimeLessThanEqualAndUserId(LocalDateTime uptoTime, Integer id);
	List<Booking> findByFromTimeGreaterThanEqualAndUserId(LocalDateTime fromTime, Integer id);
	
	@Query(value="select * from booking WHERE room_id= :roomId && ((:fromDateTime BETWEEN from_time AND upto_time) "
			+ "|| (:uptoDateTime BETWEEN from_time AND upto_time )) "
			+ " && status != 4", nativeQuery=true)
	public List<Booking> findBookingsForRoom(@Param("roomId") final int roomId, @Param("fromDateTime") final LocalDateTime fromTime, @Param("uptoDateTime") final LocalDateTime uptoTime);
	
	//public List<Booking> findByHotelId(int hotelId);

}
