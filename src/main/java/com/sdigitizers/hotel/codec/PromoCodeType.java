package com.sdigitizers.hotel.codec;

public enum PromoCodeType {
  
	 NA(0), CASHBACK(1), DISCOUNT(2);
	
	private int code;
	private PromoCodeType(int id) {
		this.code = id;
	}
	
	public int getCode() {
		return code;
	}
	
	public static PromoCodeType getValue(int code) {
		switch(code) {
		case 1 : return CASHBACK;
		case 2 : return DISCOUNT;
		
		default : return NA; 
		}
	}
}
