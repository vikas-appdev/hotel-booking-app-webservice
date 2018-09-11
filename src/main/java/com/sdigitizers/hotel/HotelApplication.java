package com.sdigitizers.hotel;

import java.time.LocalDate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.sdigitizers.hotel.fileupload.FIleStorageProperties;
import com.sdigitizers.hotel.model.User;

@SpringBootApplication
@EnableConfigurationProperties({FIleStorageProperties.class})
public class HotelApplication {

	public static void main(String[] args) {
		SpringApplication.run(HotelApplication.class, args);
		
	}
}
