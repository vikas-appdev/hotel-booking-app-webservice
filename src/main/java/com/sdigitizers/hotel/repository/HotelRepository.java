package com.sdigitizers.hotel.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sdigitizers.hotel.model.Hotel;

public interface HotelRepository extends JpaRepository<Hotel, Integer> {
	
	static final String HAVERSINE_PART = "(6371 * acos(cos(radians(:latitude)) * cos(radians(m.latitude)) "
			+ "* cos(radians(m.longitude) - radians(:longitude)) + sin(radians(:latitude)) * sin(radians(m.latitude))))";

	//@Query("SELECT m FROM Hotel m WHERE "+HAVERSINE_PART+" < :distance ORDER BY "+HAVERSINE_PART+" DESC")
	
	@Query(value="select * , 6371 * acos(cos(radians(78.3232)) * cos(radians(:latitude)) * cos(radians(:longitude) - radians(65.3234)) + sin(radians(78.3232)) * sin(radians(:latitude)) ) as distance from hotel having :distance < 1 order by distance", nativeQuery=true)
	public List<Hotel> findHotelByLocation(@Param("latitude") final double latitude, 
			@Param("longitude") final double longitude, @Param("distance") final double distance);

}
