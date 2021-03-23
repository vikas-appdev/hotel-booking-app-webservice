package com.sdigitizers.hotel.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class IssueType {
	@Id
	@GeneratedValue
	private Integer id;
	private String title;
	private String priority;
	
	@JsonIgnore
	@OneToOne(mappedBy="issueType")
	private ReportIssue reportIssue;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	public ReportIssue getReportIssue() {
		return reportIssue;
	}
	public void setReportIssue(ReportIssue reportIssue) {
		this.reportIssue = reportIssue;
	}
	
	

}
