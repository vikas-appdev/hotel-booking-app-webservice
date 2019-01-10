package com.sdigitizers.hotel.paymentgateways.payumoney;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class Controller {
	
	@PostMapping("paymentgateway/payumoney")
	public String payUMoney(@RequestBody Model model) {
		return checksumGeneration.hashCal(model);
	}

}
