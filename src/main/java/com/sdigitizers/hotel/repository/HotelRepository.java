package com.sdigitizers.hotel.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sdigitizers.hotel.model.Address;
import com.sdigitizers.hotel.model.Hotel;
import com.sdigitizers.hotel.model.User;

public interface HotelRepository extends JpaRepository<Hotel, Integer> {
	
	Optional<Hotel> findByEmail(String email);
	
	
	@Query(value="select * from hotel where city=:city", nativeQuery=true)
	public List<Hotel> findByAddressCity(@Param("city") String city);
	
	@Query(value="select * from hotel where city=:city order by id asc", nativeQuery=true)
	public List<Hotel> findByAddressCityByOrderByIdAsc(@Param("city") String city);
	
	@Query(value="select * from hotel where city=:city order by id desc", nativeQuery=true)
	public List<Hotel> findByAddressCityByOrderByIdDesc(@Param("city") String city);
	
	@Query(value="select * from hotel where city=:city order by name asc", nativeQuery=true)
	public List<Hotel> findByAddressCityByOrderByNameAsc(@Param("city") String city);
	
	@Query(value="select * from hotel where city=:city order by name desc", nativeQuery=true)
	public List<Hotel> findByAddressCityByOrderByNameDesc(@Param("city") String city);
	
	@Query(value="select * from hotel where city=:city order by city asc", nativeQuery=true)
	public List<Hotel> findByAddressCityByOrderByCityAsc(@Param("city") String city);
	
	@Query(value="select * from hotel where city=:city order by city desc", nativeQuery=true)
	public List<Hotel> findByAddressCityByOrderByCityDesc(@Param("city") String city);
	
	
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

	
	@Query(value="select r.id from room as r LEFT JOIN "
			+ "booking as b ON r.id=b.room_id WHERE  ((:fromTime >= b.from_time && :fromTime <= b.upto_time) "
			+ "|| (:uptoTime >= b.from_time && :uptoTime <= b.upto_time )) "
			+ " && b.status != 4", nativeQuery=true)
	public List<Integer> findBusyRooms(@Param("fromTime") final LocalDateTime fromTime, @Param("uptoTime") final LocalDateTime uptoTime);
}


//@Query(value="select h.*, (6371 * acos(cos(radians(:latitude)) * cos(radians(h.latitude)) * cos(radians(h.longitude) - "
//		+ "radians(:longitude)) + sin(radians(:latitude)) * sin(radians(h.latitude)) )) as distance from hotel as h JOIN room as r ON h.id=r.hotel_id LEFT JOIN "
//		+ "booking as b ON r.id=b.room_id WHERE ( ((:fromTime < b.from_time || :fromTime > b.upto_time) "
//		+ "&& (:uptoTime < b.from_time || :uptoTime > b.upto_time)) ||  ) "
//		+ "&& r.category= :category group by h.id "
//		+ "having distance < :distance order by distance", nativeQuery=true)