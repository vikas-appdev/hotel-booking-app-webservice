package com.sdigitizers.hotel.utils;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.sdigitizers.hotel.model.PromoCode;
import com.sdigitizers.hotel.model.PromocodeTransaction;
import com.sdigitizers.hotel.model.User;
import com.sdigitizers.hotel.repository.PromoCodeRepository;
import com.sdigitizers.hotel.repository.PromoCodeTransactionRepository;

@Controller
public class PromocodeValidation {
	
	@Autowired
	PromoCodeRepository codeRepository;
	@Autowired
	PromoCodeTransactionRepository codeTransactionRepository;

	public boolean validatePromoCode(String code, User user) {
		
		Optional<PromoCode> findByCode = codeRepository.findByCode(code);
		PromoCode promoCode = findByCode.get();
		if (validateDate(promoCode.getValidFrom(), promoCode.getValidTill())) {
			if (validateTime(promoCode.getTimeStart(), promoCode.getTimeEnd())) {
				if (validateMaxNoTimesUsed(promoCode, user)) {
					return true;
				} else {
					return false;
				}
			} else {
				return false;
			}
		} else {
			return false;
		}

	}

	private boolean validateMaxNoTimesUsed(PromoCode promoCode, User user) {
		List<PromocodeTransaction> findByUserAndPromoCode = codeTransactionRepository.findByUserAndPromoCode(user, promoCode);
		int count = 0;
		for (PromocodeTransaction promocodeTransaction : findByUserAndPromoCode) {
			if(promoCode.getId()==promocodeTransaction.getPromoCode().getId() && user.getId()==promocodeTransaction.getUser().getId()) {
				count++;
			}
		}
		if (promoCode.getMaxTime() > count) {
			return true;
		} else {
			return false;
		}
	}

	private boolean validateDate(LocalDateTime localDateTime, LocalDateTime localDateTime2) {
		LocalDateTime todayDate = LocalDateTime.now();
		if ((localDateTime.isBefore(todayDate)) && (localDateTime2.isAfter(todayDate))) {
			return true;
		} else {
			return false;
		}
	}

	private boolean validateTime(LocalTime validFrom, LocalTime validTill) {
		LocalTime todayTime = LocalTime.now();
		if ((validFrom.isBefore(todayTime)) && (validTill.isAfter(todayTime))) {
			return true;
		} else {
			return false;
		}
	}

}
