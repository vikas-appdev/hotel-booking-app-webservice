package com.sdigitizers.hotel.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sdigitizers.hotel.exception.NotFounWalaException;
import com.sdigitizers.hotel.model.IssueType;
import com.sdigitizers.hotel.repository.IssueTypeRepository;

@RestController
public class IssueTypeController {
	
	@Autowired
	IssueTypeRepository issueTypeRepository;
	
	@PostMapping("/issuereport")
	public IssueType insertIssueType(@RequestBody IssueType issueType) {
		return issueTypeRepository.save(issueType);
	}
	
	@GetMapping("/issuereport")
	public List<IssueType> selectAllIssueType() {
		return issueTypeRepository.findAll();
	}
	
	@GetMapping("issuereport/{id}")
	public IssueType selectIssueById(@PathVariable int id) {
		Optional<IssueType> findById = issueTypeRepository.findById(id);
		if (!findById.isPresent())
			throw new NotFounWalaException("Issue Not found with id"+id);
		
		
		return findById.get();
	}
	
	@PutMapping("issuereport/{id}")
	public IssueType updateIssue(@PathVariable int id, @RequestBody IssueType issueType) {
		Optional<IssueType> findById = issueTypeRepository.findById(id);
		if (!findById.isPresent())
			throw new NotFounWalaException("Issue Not found with id"+id);
		
		IssueType issueType2 = findById.get();
		issueType.setId(issueType2.getId());
		
		return issueTypeRepository.save(issueType);
	}
	
	@DeleteMapping("/issuereport/{id}")
	public void deleteIssueById(@PathVariable int id) {
		issueTypeRepository.deleteById(id);
	}

}
