package com.sdigitizers.hotel.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sdigitizers.hotel.model.BookingReview;

public interface BookingReviewRepository extends JpaRepository<BookingReview, Long> {

}
