package com.sdigitizers.hotel.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sdigitizers.hotel.model.IssueType;

public interface IssueTypeRepository extends JpaRepository<IssueType, Integer> {

}
