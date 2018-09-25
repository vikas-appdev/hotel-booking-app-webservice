package com.sdigitizers.hotel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sdigitizers.hotel.model.BookingReview;

public interface BookingReviewRepository extends JpaRepository<BookingReview, Long> {
	
	@Query(value="select b.* from booking b join room r on b.room_id=r.id join hotel h on h.hotel_id = h.id where h.id = :hotelid", nativeQuery=true)
	List<BookingReview> findByHotel(@Param("hotelid") int hotelId);
	
	@Query(value="select b.* from booking b join room r on b.room_id=r.id where r.id = :roomid", nativeQuery=true)
	List<BookingReview> findByRoom(@Param("roomid") int roomId);
	
	@Query(value="select b.* from booking b join user u on b.user_id=u.id where u.id = :userid", nativeQuery=true)
	List<BookingReview> findByUser(@Param("userid") int userId);
	

}
