package com.sdigitizers.hotel.codec;

public enum PaymentMode {
  
	 NA(0), CARD(1), CASH(2);
	
	private int code;
	private PaymentMode(int id) {
		this.code = id;
	}
	
	public int getCode() {
		return code;
	}
	
	public static PaymentMode getValue(int code) {
		switch(code) {
		case 1 : return CARD;
		case 2 : return CASH;
		default : return NA; 
		}
	}
}
