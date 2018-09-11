package com.sdigitizers.hotel.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.sdigitizers.hotel.UserNotFoundException;
import com.sdigitizers.hotel.model.Hotel;
import com.sdigitizers.hotel.model.Transaction;
import com.sdigitizers.hotel.model.User;
import com.sdigitizers.hotel.repository.HotelRepository;
import com.sdigitizers.hotel.repository.TransactionRepository;
import com.sdigitizers.hotel.repository.UserRepository;

@RestController
public class TransactionController {

	@Autowired
	private TransactionRepository transactionRepository;
	

	@GetMapping("transaction")
	public List<Transaction> retriveAllHotels() {
		return transactionRepository.findAll();
	}
	
	
	
	@PostMapping("transactions")
	public ResponseEntity<Object> createHotel(@RequestBody Transaction transaction) {
		Transaction savedTransaction = transactionRepository.save(transaction);
		
		
		URI location = ServletUriComponentsBuilder
		.fromCurrentRequest()
		.path("/{id}")
		.buildAndExpand(savedTransaction.getId()).toUri();
		
		return ResponseEntity.created(location).build();
		
	}

}