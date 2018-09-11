package com.sdigitizers.hotel.codec;

public enum TransactionType {
  
	 NA(0), CREDIT(1), DEBIT(2);
	
	private int code;
	private TransactionType(int id) {
		this.code = id;
	}
	
	public int getCode() {
		return code;
	}
	
	public static TransactionType getValue(int code) {
		switch(code) {
		case 1 : return CREDIT;
		case 2 : return DEBIT;
		default : return NA; 
		}
	}
}
