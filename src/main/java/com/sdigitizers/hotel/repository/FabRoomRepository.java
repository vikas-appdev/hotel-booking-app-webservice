package com.sdigitizers.hotel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sdigitizers.hotel.model.FabRoom;
import com.sdigitizers.hotel.model.FabRoomCompositeKey;
import com.sdigitizers.hotel.model.User;

public interface FabRoomRepository extends JpaRepository<FabRoom, Integer> {
	
	
	List<FabRoom> findByUserId(int userId);

}
