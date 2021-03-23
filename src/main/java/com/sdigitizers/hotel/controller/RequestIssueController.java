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
import com.sdigitizers.hotel.model.ReportIssue;
import com.sdigitizers.hotel.repository.ReportIssueRepository;

@RestController
public class RequestIssueController {
	
	@Autowired
	ReportIssueRepository issueRepository;
	
	@PostMapping("/reportissue")
	public ReportIssue saveReportIssue(@RequestBody ReportIssue reportIssue) {
		return issueRepository.save(reportIssue);
	}
	
	@GetMapping("/reportissue")
	public List<ReportIssue> selectAllReportIssue(){
		return issueRepository.findAll();
	}
	
	@GetMapping("/reportissue/{id}")
	public ReportIssue selectReportIssueById(@PathVariable int id) {
		Optional<ReportIssue> findById = issueRepository.findById(id);
		if (!findById.isPresent()) {
			throw new NotFounWalaException("issue not found with id: "+id);
		}
		
		return findById.get();
	}
	
	@DeleteMapping("/reportissue/{id}")
	public void deleteReportIssue(@PathVariable int id) {
		issueRepository.deleteById(id);
	}
	
	@PutMapping("/reportissue/{id}")
	public ReportIssue updateIssue(@PathVariable int id, @RequestBody ReportIssue reportIssue) {
		Optional<ReportIssue> findById = issueRepository.findById(id);
		if (!findById.isPresent()) {
			throw new NotFounWalaException("issue not found with id: "+id);
		}
		
		ReportIssue reportIssue2 = findById.get();
		
		reportIssue.setId(reportIssue2.getId());
		
		return issueRepository.save(reportIssue);
	}

}
