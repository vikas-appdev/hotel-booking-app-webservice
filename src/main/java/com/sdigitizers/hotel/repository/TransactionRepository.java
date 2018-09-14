package com.sdigitizers.hotel.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sdigitizers.hotel.model.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {	

}
