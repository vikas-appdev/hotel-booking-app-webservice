package com.sdigitizers.hotel.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sdigitizers.hotel.model.Hotel;

public interface HotelRepository extends JpaRepository<Hotel, Integer> {
	/*
	static final String HAVERSINE_PART = "(6371 * acos(cos(radians(:latitude)) * cos(radians(m.latitude)) "
			+ "* cos(radians(m.longitude) - radians(:longitude)) + sin(radians(:latitude)) * sin(radians(m.latitude))))";*/

	//@Query("SELECT m FROM Hotel m WHERE "+HAVERSINE_PART+" < :distance ORDER BY "+HAVERSINE_PART+" DESC")
	
	@Query(value="select h.*, (6371 * acos(cos(radians(:latitude)) * cos(radians(h.latitude)) * cos(radians(h.longitude) - "
			+ "radians(:longitude)) + sin(radians(:latitude)) * sin(radians(h.latitude)) )) as distance from hotel as h JOIN room as r ON h.id=r.hotel_id"
			+ " WHERE r.category= :category group by h.id "
			+ "having distance < :distance order by distance", nativeQuery=true)
	
	public List<Hotel> findHotelByLocation(@Param("latitude") final double latitude, 
			@Param("longitude") final double longitude, @Param("distance") final double distance, 
			@Param("category") final int category);
	
	
	@Query(value="select h.id from hotel as h JOIN room as r ON h.id=r.hotel_id LEFT JOIN "
			+ "booking as b ON r.id=b.room_id WHERE  (:fromTime < b.from_time || :fromTime > b.upto_time) "
			+ "&& (:uptoTime < b.from_time || :uptoTime > b.upto_time) ", nativeQuery=true)
	public List<Hotel> findAvailableRooms(@Param("fromTime") final LocalDateTime fromTime, @Param("uptoTime") final LocalDateTime uptoTime);

	
	@Query(value="select h.id from hotel as h JOIN room as r ON h.id=r.hotel_id LEFT JOIN "
			+ "booking as b ON r.id=b.room_id WHERE  (:fromTime > b.from_time && :fromTime < b.upto_time) "
			+ "|| (:uptoTime > b.from_time && :uptoTime < b.upto_time) ", nativeQuery=true)
	public List<Hotel> findBusyRooms(@Param("fromTime") final LocalDateTime fromTime, @Param("uptoTime") final LocalDateTime uptoTime);
}


//@Query(value="select h.*, (6371 * acos(cos(radians(:latitude)) * cos(radians(h.latitude)) * cos(radians(h.longitude) - "
//		+ "radians(:longitude)) + sin(radians(:latitude)) * sin(radians(h.latitude)) )) as distance from hotel as h JOIN room as r ON h.id=r.hotel_id LEFT JOIN "
//		+ "booking as b ON r.id=b.room_id WHERE ( ((:fromTime < b.from_time || :fromTime > b.upto_time) "
//		+ "&& (:uptoTime < b.from_time || :uptoTime > b.upto_time)) ||  ) "
//		+ "&& r.category= :category group by h.id "
//		+ "having distance < :distance order by distance", nativeQuery=true)