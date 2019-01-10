package com.sdigitizers.hotel.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sdigitizers.hotel.UserNotFoundException;
import com.sdigitizers.hotel.codec.TransactionType;
import com.sdigitizers.hotel.model.AppConfig;
import com.sdigitizers.hotel.model.CustomerWalletTxn;
import com.sdigitizers.hotel.model.User;
import com.sdigitizers.hotel.repository.AppConfigRepository;
import com.sdigitizers.hotel.repository.CustomerWalletTxnRepository;
import com.sdigitizers.hotel.repository.UserRepository;
import com.sdigitizers.hotel.utils.RandomString;

@RestController
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CustomerWalletTxnRepository cwt;
	
	@Autowired
	private AppConfigRepository appconfig;
	
	
	
	@GetMapping("/users/{email}")
	public Optional<User> retriveUserByEmail(@PathVariable String email) {
		Optional<User> user = userRepository.findByEmail(email);
		if(!user.isPresent())
			throw new UserNotFoundException("id- "+email);
		
		return user;
		
	}
	
	@GetMapping("users/mobilenumber/{phone}")
	public boolean findUserByPhone(@PathVariable long phone) {
		
		Optional<User> findByPhone = userRepository.findByPhone(phone);
		if (findByPhone.isPresent()) {
			return true;
		}
		return false;
	}
	
	@GetMapping("/users/by/{id}")
	public Optional<User> retriveUserById(@PathVariable int id) {
		Optional<User> user = userRepository.findById(id);
		if(!user.isPresent())
			throw new UserNotFoundException("id- "+id);
		
		return user;
		
	}
	
	@PostMapping("login")
	public User retriveUserByEmailAndPassword(@RequestBody String email, @RequestBody String pass) {
		return userRepository.findByEmailAndPassword(email, pass);
	}
	
	
	@GetMapping("/users")
	public List<User> retriveAllUsers(){
		return userRepository.findAll();
		
	}
	
	@PostMapping("/users/{refcode}")
	public User createUser(@RequestBody User user, @PathVariable String refcode) {
		//boolean present = false;
		double amount = 0.0;
		List<AppConfig> findAll = appconfig.findAll();
		for (AppConfig appConfig : findAll) {
			if (appConfig.getType().equals("referral_bonus_amount")) {
				amount = Double.parseDouble(appConfig.getDescription());
			}
		}
		
		
		String randomAlphaNumeric = RandomString.randomAlphaNumeric(6);
		user.setReferralCode(randomAlphaNumeric);
		User save = userRepository.save(user);
		
		
		if(refcode!=null) {
			User user2 = userRepository.findByReferralCode(refcode);
			CustomerWalletTxn txn = new CustomerWalletTxn();
			txn.setAmount(amount);
			txn.setDate(LocalDate.now());
			txn.setRemarks("remarks");
			txn.setTxnRef("reference");
			txn.setTxnType(TransactionType.CREDIT);
			txn.setUser(user2);
			cwt.save(txn);
			double userWalletBalance = user2.getWalletBalance()+amount;
			user2.setWalletBalance(userWalletBalance);
			userRepository.save(user2);
			
			
			CustomerWalletTxn txn2 = new CustomerWalletTxn();
			txn2.setAmount(amount);
			txn2.setDate(LocalDate.now());
			txn2.setRemarks("Referral Bonus");
			txn2.setTxnRef(refcode);
			txn2.setTxnType(TransactionType.CREDIT);
			txn2.setUser(save);
			cwt.save(txn2);
			save.setWalletBalance(amount);
			return userRepository.save(save);
			
		}
//		List<User> findAll = userRepository.findAll();
//		for (User user2 : findAll) {
//			if (user2.getReferralCode().equals(randomAlphaNumeric)) {
//				present = true;
//			}
//		}
//		
//		if(present==false) {
//			user.setReferralCode(randomAlphaNumeric);
//		}
		
//		user.setReferralCode(randomAlphaNumeric);
		return save;
		
		/*URI location = ServletUriComponentsBuilder
		.fromCurrentRequest()
		.path("/{id}")
		.buildAndExpand(savedUser.getId()).toUri();
		
		return ResponseEntity.created(location).build();*/
		
	}
	
	public String check(String random) {
		
		return null;
	}
	
	@PutMapping("/users/{id}")
	public User updateUser(@RequestBody User user,@PathVariable String id) {
		
		Optional<User> userOptional = userRepository.findByEmail(id);
		if(!userOptional.isPresent())
			throw new UserNotFoundException("id- "+id);

		User user2 = userOptional.get();
		
		user.setId(user2.getId());
		
		
		
		
		return userRepository.save(user);

		
		
	}
	

}
