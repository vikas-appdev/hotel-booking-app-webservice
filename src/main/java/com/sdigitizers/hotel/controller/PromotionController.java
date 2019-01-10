package com.sdigitizers.hotel.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sdigitizers.hotel.model.PromoCode;
import com.sdigitizers.hotel.model.Promotion;
import com.sdigitizers.hotel.repository.PromoCodeRepository;
import com.sdigitizers.hotel.repository.PromotionRepository;



@RestController
public class PromotionController {
	@Autowired
	PromotionRepository promotionRepository;
	
	@Autowired
	PromoCodeRepository promocodeRepository;
	
	@PostMapping("/promotion/promocode/{promocodeid}")
	public Promotion addPromotion(@PathVariable int promocodeid, @RequestBody Promotion promotion) {
		Optional<PromoCode> optional = promocodeRepository.findById(promocodeid);
		PromoCode promoCode = optional.get();
		promotion.setPromoCode(promoCode);
		return promotionRepository.save(promotion);
	}
	
	@GetMapping("/promotions")
	public List<Promotion> getPromomotions(){
		return promotionRepository.findAll();
	}
	
	@PutMapping("/promotion/{id}")
	public Promotion updatePromotion(@PathVariable int id, @RequestBody Promotion promotion) {
		Optional<Promotion> optional = promotionRepository.findById(id);
		Promotion promotion2 = optional.get();
		promotion.setId(promotion2.getId());
		
		return promotionRepository.save(promotion);
		
	}

}
