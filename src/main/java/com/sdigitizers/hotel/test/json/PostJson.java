package com.sdigitizers.hotel.test.json;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sdigitizers.hotel.model.User;

public class PostJson {
	
	public static void main(String[] args) throws JsonGenerationException, JsonMappingException, IOException {
		
		User user = new User();
		
		user.setAccountCreationTime(LocalDateTime.now());
		user.setActive(true);
		user.setCountryCode("91");
		user.setDateOfBirth(LocalDate.now());
		user.setEmail("v.ray");
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.writeValue(System.out, user);
		
		
	}

}
