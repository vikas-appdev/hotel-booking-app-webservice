package com.sdigitizers.hotel.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sdigitizers.hotel.codec.BookingStatus;
import com.sdigitizers.hotel.codec.PaymentStatus;
import com.sdigitizers.hotel.codec.TransactionType;
import com.sdigitizers.hotel.model.AppConfig;
import com.sdigitizers.hotel.model.Booking;
import com.sdigitizers.hotel.model.CustomerWalletTxn;
import com.sdigitizers.hotel.model.Transaction;
import com.sdigitizers.hotel.model.User;
import com.sdigitizers.hotel.repository.AppConfigRepository;
import com.sdigitizers.hotel.repository.BookingRepository;
import com.sdigitizers.hotel.repository.CustomerWalletTxnRepository;
import com.sdigitizers.hotel.repository.TransactionRepository;
import com.sdigitizers.hotel.repository.UserRepository;

@RestController
public class TransactionController {

	@Autowired
	private TransactionRepository transactionRepository;
	@Autowired
	private BookingRepository bookingRepository;
	@Autowired
	private AppConfigRepository appConfigRepository;
	@Autowired
	private CustomerWalletTxnRepository customerWalletTxn;
	@Autowired
	private UserRepository userRepository;
	

	@GetMapping("transaction")
	public List<Transaction> retriveAllHotels() {
		return transactionRepository.findAll();
	}
	
	@GetMapping("transaction/user/{userid}")
	public List<Transaction> getTransactionByUser(@PathVariable int userid){
		List<Transaction> findAll = transactionRepository.findAll();
		List<Transaction> trans = new ArrayList<>();
		for (Transaction transaction : findAll) {
			if (transaction.getBooking().getUser().getId() == userid) {
				trans.add(transaction);
			}
		}
		return trans;
	}
	
	@GetMapping("transaction/by/hotel/{hotelid}")
	public List<Transaction> getTransactionByHotel(@PathVariable int hotelid){
		List<Transaction> findAll = transactionRepository.findAll();
		List<Transaction> trans = new ArrayList<>();
		for (Transaction transaction : findAll) {
			if (transaction.getBooking().getRoom().getHotel().getId() == hotelid) {
				trans.add(transaction);
			}
		}
		return trans;
	}
	
	@PostMapping("transactions")
	public long createHotel(@RequestBody Transaction transaction) {
		Transaction savedTransaction = transactionRepository.save(transaction);
		
		Booking booking2 = savedTransaction.getBooking();
		double amount = savedTransaction.getAmount();
		int usertype = booking2.getUser().getUserType();
		User user = booking2.getUser();
		
		if(usertype==1) {
			Optional<AppConfig> optional = appConfigRepository.findByType("agent_commission_rate");
			AppConfig appConfig = optional.get();
			double agentCommissionRate = Double.parseDouble(appConfig.getDescription());
			double finalamount =(amount * agentCommissionRate)/100;
			CustomerWalletTxn txn = new CustomerWalletTxn();
			txn.setUser(user);
			txn.setAmount(finalamount);
			txn.setDate(LocalDate.now());
			txn.setRemarks("Room Booking");
			txn.setTxnRef("Booking id"+booking2.getId());
			txn.setTxnType(TransactionType.CREDIT);
			
			customerWalletTxn.save(txn);
			double walletBalance = user.getWalletBalance()+finalamount;
			user.setWalletBalance(walletBalance);
			userRepository.save(user);
			booking2.setPaymentStatus(PaymentStatus.PAID);
			bookingRepository.save(booking2);
			return savedTransaction.getId();
		}else {
			booking2.setPaymentStatus(PaymentStatus.PAID);
			bookingRepository.save(booking2);
			System.out.println(savedTransaction.getId());
			//Long id = savedTransaction.getId();
			return savedTransaction.getId();
		}
		
		
	}
	@PutMapping("transaction/{id}")
	public ResponseEntity<Object> updateTransaction(@RequestBody Transaction transaction,@PathVariable long id) {
		
		Optional<Transaction> transactionOptional = transactionRepository.findById(id);
		
		if (!transactionOptional.isPresent())
			return ResponseEntity.notFound().build();

		Transaction txn = transactionOptional.get();
		Booking booking = txn.getBooking();
		booking.setStatus(BookingStatus.CANCELLED);
		transaction.setBooking(booking);
		transaction.setId(id);
		
		
		
		
		transactionRepository.save(transaction);

		return ResponseEntity.noContent().build();
		
	}
	
	
	
	

}
