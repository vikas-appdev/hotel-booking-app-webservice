package com.sdigitizers.hotel.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sdigitizers.hotel.model.Booking;

public interface BookingRepository extends JpaRepository<Booking, Integer> {

}
