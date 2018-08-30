package com.sdigitizers.hotel.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.sdigitizers.hotel.UserNotFoundException;
import com.sdigitizers.hotel.model.FabRoom;
import com.sdigitizers.hotel.model.User;
import com.sdigitizers.hotel.repository.FabRoomRepository;
import com.sdigitizers.hotel.repository.UserRepository;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

@RestController
public class FabRoomController {
	
	@Autowired
	private FabRoomRepository fabRoomRepository;
	
	
	
	@GetMapping("fabrooms/{id}")
	public Resource<FabRoom> retriveUser(@PathVariable int id) {
		Optional<FabRoom> room = fabRoomRepository.findById(id);
		if(!room.isPresent())
			throw new UserNotFoundException("id- "+id);
		
		//HATEOAS
		Resource<FabRoom> resource = new Resource<FabRoom>(room.get());
		ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retriveAllUsers());
		resource.add(linkTo.withRel("all-rooms"));
		
		return resource;
		
	}
	
	
	@GetMapping("/fabrooms")
	public List<FabRoom> retriveAllUsers(){
		return fabRoomRepository.findAll();
		
	}
	
	
	
	

}