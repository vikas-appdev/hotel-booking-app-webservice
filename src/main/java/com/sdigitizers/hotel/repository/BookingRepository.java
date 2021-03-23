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
	
	List<Booking> findByUserIdNot(int userId);
	
	@Query(value="select * from booking WHERE room_id= :roomId && ((:fromDateTime BETWEEN from_time AND upto_time) "
			+ "|| (:uptoDateTime BETWEEN from_time AND upto_time )) "
			+ " && status != 4", nativeQuery=true)
	public List<Booking> findBookingsForRoom(@Param("roomId") final int roomId, @Param("fromDateTime") final LocalDateTime fromTime, @Param("uptoDateTime") final LocalDateTime uptoTime);
	
	
	@Query(value="select * from booking b join room r on b.room_id=r.id join hotel h on r.hotel_id=h.id WHERE h.id= :hotelId && b.from_time <= :time order by from_time", nativeQuery=true)
	public List<Booking> findPastBookingsForHotel(@Param("hotelId") final int hotelId, @Param("time") final LocalDateTime time);
	
	
	@Query(value="select * from booking b join room r on b.room_id=r.id join hotel h on r.hotel_id=h.id WHERE h.id= :hotelId && b.from_time > :time order by from_time", nativeQuery=true)
	public List<Booking> findUpcomingBookingsForHotel(@Param("hotelId") final int hotelId, @Param("time") final LocalDateTime time);
	
	@Query(value="select * from booking b join room r on b.room_id=r.id join hotel h on r.hotel_id=h.id WHERE h.id= :hotelId order by from_time", nativeQuery=true)
	public List<Booking> findAllBookingsForHotel(@Param("hotelId") final int hotelId);
	
	//public List<Booking> findByHotelId(int hotelId);
	
	@Query(value="select * from booking b join room r on b.room_id=r.id join hotel h on r.hotel_id=h.id WHERE h.id= :hotelId && :time between b.from_time and b.upto_time order by from_time", nativeQuery=true)
	public List<Booking> findUpcomingBookingsForHotelBySingleDate(@Param("hotelId") final int hotelId, @Param("time") final LocalDateTime time);

}
