package com.sdigitizers.hotel.test.json;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.sdigitizers.hotel.model.Address;
import com.sdigitizers.hotel.model.User;

public class ReadJson {
	
	public static void main(String[] args) throws JsonGenerationException, JsonMappingException, IOException, UnirestException {
		ObjectMapper objectMapper = new ObjectMapper();
		
		/*User user = new User();
		Address add = new Address();
		add.setCity("Jorhat");
		add.setLocationCoordinates("985,858");
		add.setCountry("India");
		add.setPincode(785001);
		add.setState("Assam");
		add.setStreet("fancy ali");
		
		user.setAccountCreationTime(LocalDateTime.now());
		user.setActive(true);
		user.setDateOfBirth(LocalDate.now());
		user.setId(256);
		user.setCountryCode("+91");
		user.setEmail("v.ray96610@gmail.com");
		user.setGender("male");
		user.setImageLink("https://test.com/tes.png");
		user.setName("Vikas Roy");
		user.setPassword("qwerty");
		user.setPhone(7002132366L);
		user.setAddress(add);
		
		
		
		
		objectMapper.writeValue(System.out, user);*/
		
		
		
		
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
