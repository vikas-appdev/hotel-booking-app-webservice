package com.sdigitizers.hotel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sdigitizers.hotel.model.PromoCode;
import com.sdigitizers.hotel.model.User;
import com.sdigitizers.hotel.repository.PromoCodeRepository;
import com.sdigitizers.hotel.utils.PromocodeValidation;

@RestController
public class PromoCodeController {
	
	@Autowired
	private PromoCodeRepository codeRepository;
	
	@Autowired
	private PromocodeValidation promocodeValidation;
	
	@PostMapping("/promocode")
	public PromoCode savePromoCode(@RequestBody PromoCode code) {
		return codeRepository.save(code);
	}
	
	@DeleteMapping("/promocode/{id}")
	public void deletePromoCode(@PathVariable int id) {
		codeRepository.deleteById(id);
	}
	
	@PutMapping("/promocode")
	public void updatePromoCode(@RequestBody PromoCode code) {
		codeRepository.save(code);
	}
	
	@GetMapping("/promocode")
	public List<PromoCode> getPromoCode() {
		return codeRepository.findAll();
	}
	
	@PostMapping("/promocodeval/{code}")
	public boolean validatePromoCodeController(@PathVariable String code, @RequestBody User user) {
		System.out.println(code);
		System.out.println(user);
		return promocodeValidation.validatePromoCode(code, user);
	}

}
