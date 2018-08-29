package com.sdigitizers.hotel.test.json;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sdigitizers.hotel.model.User;

public class ReadJson {
	
	public static void main(String[] args) throws JsonGenerationException, JsonMappingException, IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		
		User user = new User();
		
		user.setAccountCreationTime(LocalDateTime.now());
		user.setActive(true);
		user.setDateOfBirth(LocalDate.now());
		user.setId(256);
		
		objectMapper.writeValue(System.out, user);
		
		
		
		/*try {
			User user = objectMapper.readValue(new URL("http://sd-hotel-app.herokuapp.com/users/manishmundra001@gmail.com"), User.class);
			System.out.println(user.getName());
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}*/
	}

}
