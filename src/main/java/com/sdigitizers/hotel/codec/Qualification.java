package com.sdigitizers.hotel.codec;

public enum Qualification {
  
	 NA(0), ACTIVE(1), ACKNOWLEDGED(2), NOT_REACHED(3), CANCELLED(4), COMPLETED(5), CANCELLED_REFUNDED(6);
	
	private int code;
	private Qualification(int id) {
		this.code = id;
	}
	
	public int getCode() {
		return code;
	}
	
	public static Qualification getValue(int code) {
		switch(code) {
		case 1 : return ACTIVE;
		case 2 : return ACKNOWLEDGED;
		case 3 : return NOT_REACHED;
		case 4 : return CANCELLED;
		case 5 : return COMPLETED;
		case 6 : return CANCELLED_REFUNDED;
		default : return NA; 
		}
	}
}
