package com.sdigitizers.hotel.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sdigitizers.hotel.codec.TransactionType;
import com.sdigitizers.hotel.model.AppConfig;
import com.sdigitizers.hotel.model.CustomerWalletTxn;
import com.sdigitizers.hotel.model.User;
import com.sdigitizers.hotel.repository.AppConfigRepository;
import com.sdigitizers.hotel.repository.CustomerWalletTxnRepository;
import com.sdigitizers.hotel.repository.UserRepository;



@RestController
public class CustomerWalletTxnController {
	@Autowired
	private CustomerWalletTxnRepository walletTxnRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private AppConfigRepository appConfigRepository;
	
	@PostMapping("/wallettxn/{userid}")
	public User add(@RequestBody CustomerWalletTxn txn, @PathVariable int userid) {
		
		
		
		Optional<User> findById = userRepository.findById(userid);
		User user2 = findById.get();
		txn.setUser(user2);
		walletTxnRepository.save(txn);
		double amount = txn.getAmount();
		
		double walletBalance;
		if(txn.getTxnType()==TransactionType.CREDIT) {
		walletBalance = user2.getWalletBalance()+amount;
		}
		else {
			walletBalance = user2.getWalletBalance()-amount;	
		}
		user2.setWalletBalance(walletBalance);
		
		return userRepository.save(user2);

	}
	@DeleteMapping("/wallettxn/{id}")
	public void delete(@PathVariable int id) {
		walletTxnRepository.deleteById(id);
	}
	
	
	
	

}
