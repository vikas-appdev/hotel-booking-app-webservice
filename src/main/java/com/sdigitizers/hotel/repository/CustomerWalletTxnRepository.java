package com.sdigitizers.hotel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sdigitizers.hotel.model.CustomerWalletTxn;

public interface CustomerWalletTxnRepository extends JpaRepository<CustomerWalletTxn, Integer> {
	
	List<CustomerWalletTxn> findByUserId(int userid);

}
