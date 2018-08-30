package com.sdigitizers.hotel.test.json;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DateController {
	
	@RequestMapping("/localDate")
    public LocalDate todayLocalDate() {
        return LocalDate.now();
    }
	
	@RequestMapping("/localDateTime")
    public LocalDateTime todayLocalDateTime() {
        return LocalDateTime.now();
    }

}
