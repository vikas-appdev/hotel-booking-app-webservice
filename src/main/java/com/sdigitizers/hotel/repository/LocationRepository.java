package com.sdigitizers.hotel.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sdigitizers.hotel.model.Hotel;
import com.sdigitizers.hotel.model.Location;

public interface LocationRepository extends JpaRepository<Location, Integer> {
	
	@Query(value="select h.*, (6371 * acos(cos(radians(:latitude)) * cos(radians(h.latitude)) * cos(radians(h.longitude) - "
			+ "radians(:longitude)) + sin(radians(:latitude)) * sin(radians(h.latitude)) )) as distance from hotel as h "
			+ "group by h.id having distance < 1 order by distance", nativeQuery=true)
	
	public List<Hotel> findHotelByLocation(@Param("latitude") final double latitude, 
			@Param("longitude") final double longitude);

}
