package com.sdigitizers.hotel.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sdigitizers.hotel.model.AppConfig;
import com.sdigitizers.hotel.repository.AppConfigRepository;

@RestController
public class AppConfigController {
	
	@Autowired
	AppConfigRepository appConfigRepository;
	
	@GetMapping("/appconfig")
	public HashMap<String,String> getAppConfig() {
		List<AppConfig> findAll = appConfigRepository.findAll();
		HashMap<String, String> config = new HashMap<>();
		for (AppConfig appConfig : findAll) {
			config.put(appConfig.getType(), appConfig.getDescription());
		}
		
		return config;
	}
	
	@PostMapping("/appconfig")
	public AppConfig saveAppConfig(@RequestBody AppConfig appConfig) {
		
		Optional<AppConfig> optional = appConfigRepository.findByType(appConfig.getType());
		if (optional.isPresent()) {
			AppConfig config = optional.get();
			appConfig.setId(config.getId());
		}
		
		return appConfigRepository.save(appConfig);
	}
	
	//check type and then update
	
	
	
}
