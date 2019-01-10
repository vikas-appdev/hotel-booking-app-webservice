package com.sdigitizers.hotel.codec;

public enum TransactionStatus {
  
	 NA(0), SUCCESSFUL(1), FAILED(2), PENDING(3), CANCELLED(4);
	
	private int code;
	private TransactionStatus(int id) {
		this.code = id;
	}
	
	public int getCode() {
		return code;
	}
	
	public static TransactionStatus getValue(int code) {
		switch(code) {
		case 1 : return SUCCESSFUL;
		case 2 : return FAILED;
		case 3 : return PENDING;
		case 4 : return CANCELLED;
		default : return NA; 
		}
	}
}
