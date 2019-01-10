package com.sdigitizers.hotel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.sdigitizers.hotel.model.PromoCode;
import com.sdigitizers.hotel.model.PromocodeTransaction;
import com.sdigitizers.hotel.repository.PromoCodeRepository;
import com.sdigitizers.hotel.repository.PromoCodeTransactionRepository;

public class PromoCodeTxnController {
	
	@Autowired
	private PromoCodeTransactionRepository txnRepository;
	
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
	
	

}
