package com.sdigitizers.hotel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.sdigitizers.hotel.model.Gsttarrif;
import com.sdigitizers.hotel.repository.GsttarrifRepository;

@Repository
public class GstController {
	
	@Autowired
	private GsttarrifRepository gsttarrifRepository;
	
	@GetMapping("/gstrates")
	public List<Gsttarrif> getAllgstTarrif(){
		return gsttarrifRepository.findAll();
	}
	
	@PostMapping("/gstrates")
	public Gsttarrif addGstTarrif(@RequestBody Gsttarrif gsttarrif) {
		return gsttarrifRepository.save(gsttarrif);
	}
	
	

}
