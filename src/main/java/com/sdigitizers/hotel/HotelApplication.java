package com.sdigitizers.hotel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.sdigitizers.hotel.fileupload.FIleStorageProperties;

@SpringBootApplication
@EnableConfigurationProperties({FIleStorageProperties.class})
public class HotelApplication {

	public static void main(String[] args) {
		SpringApplication.run(HotelApplication.class, args);
		
	}
}
