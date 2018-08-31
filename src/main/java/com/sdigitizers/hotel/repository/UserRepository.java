package com.sdigitizers.hotel.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sdigitizers.hotel.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	
	Optional<User> findByEmail(String email);
	
	
	/*@Query("select u from User u where u.email = :email or u.name = :name")
	User findByEmailOrName(@Param("email") String email,
	                                 @Param("name") String name);*/
	
	User findByEmailAndPassword(String email, String password);
	

}
