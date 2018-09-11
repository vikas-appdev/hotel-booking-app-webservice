package com.sdigitizers.hotel.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sdigitizers.hotel.model.FabRoom;
import com.sdigitizers.hotel.model.User;

public interface FabRoomRepository extends JpaRepository<FabRoom, Integer> {
	
	FabRoom findByUserId(int userId);

}
