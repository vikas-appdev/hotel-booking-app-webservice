package com.sdigitizers.hotel.codec;

public enum PaymentStatus {
	
	NA(0), PENDING(1), PAID(2);
	
	private int code;
	private PaymentStatus(int id) {
		this.code = id;
	}
	
	public int getCode() {
		return code;
	}
	
	public static PaymentStatus getValue(int code) {
		switch(code) {
		case 1 : return PENDING;
		case 2 : return PAID;
		
		default : return NA; 
		}
	}

}
