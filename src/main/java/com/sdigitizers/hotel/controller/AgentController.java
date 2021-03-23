package com.sdigitizers.hotel.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.sdigitizers.hotel.exception.NotFounWalaException;
import com.sdigitizers.hotel.model.Agent;
import com.sdigitizers.hotel.model.User;
import com.sdigitizers.hotel.repository.AgentRepository;
import com.sdigitizers.hotel.repository.UserRepository;

@Repository
public class AgentController {
	
	@Autowired
	private AgentRepository agentRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@PostMapping("/agent/{userid}")
	public Agent saveAgentDetails(@PathVariable int userid,@RequestBody Agent agent) {
		Optional<User> optional = userRepository.findById(userid);
		if (!optional.isPresent()) {
			throw new NotFounWalaException("id:"+userid);
		}
		User user = optional.get();
		
		agent.setUser(user);
		
		return agentRepository.save(agent);
	}
	
	

}
