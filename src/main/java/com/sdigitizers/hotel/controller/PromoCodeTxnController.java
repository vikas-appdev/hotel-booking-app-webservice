package com.sdigitizers.hotel.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sdigitizers.hotel.exception.NotFounWalaException;
import com.sdigitizers.hotel.model.PromoCode;
import com.sdigitizers.hotel.model.PromocodeTransaction;
import com.sdigitizers.hotel.model.User;
import com.sdigitizers.hotel.repository.PromoCodeRepository;
import com.sdigitizers.hotel.repository.PromoCodeTransactionRepository;
import com.sdigitizers.hotel.repository.UserRepository;

@RestController
public class PromoCodeTxnController {
	
	@Autowired
	private PromoCodeTransactionRepository txnRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PromoCodeRepository promocodeRepository;
	
	@PostMapping("/promocodetxn")
	public PromocodeTransaction savePromoCode(@RequestBody PromocodeTransaction code) {
		return txnRepository.save(code);
	}
	
	@DeleteMapping("/promocodetxn/{id}")
	public void deletePromoCode(@PathVariable int id) {
		txnRepository.deleteById(id);
	}
	
	@PutMapping("/promocodetxn")
	public void updatePromoCode(@RequestBody PromocodeTransaction code) {
		txnRepository.save(code);
	}
	
	@GetMapping("/promocodetxn")
	public List<PromocodeTransaction> getPromoCode() {
		return txnRepository.findAll();
	}
	
	@GetMapping("/promocodetxn/user/{userid}")
	public List<PromocodeTransaction> getPromocodeTxnsByUser(@PathVariable int userid) {
		Optional<User> optional = userRepository.findById(userid);
		if (!optional.isPresent()) {
			throw new NotFounWalaException("User Not Found With Id: "+userid);
		}
		
		return txnRepository.findByUser(optional.get());
		
	}
	
	@GetMapping("promocodetxn/promocode/{code}")
	public List<PromocodeTransaction> getPromocodeTxnsByPromoCode(@PathVariable String code) {
		Optional<PromoCode> optional = promocodeRepository.findByCode(code);
		
		if(!optional.isPresent())
			throw new NotFounWalaException("No Promocode Found");
		
		return txnRepository.findByPromoCodeId(optional.get().getId());
		
	}
	
	@GetMapping("promocodetxn/period/{startdate}/{enddate}")
	public List<PromocodeTransaction> getPromocodeByPeriod(@PathVariable @DateTimeFormat(iso=ISO.DATE) LocalDate startdate
			, @PathVariable @DateTimeFormat(iso=ISO.DATE) LocalDate enddate){
		
		
		return txnRepository.findByTimeBetween(startdate, enddate);
		
	}
	
	

}
