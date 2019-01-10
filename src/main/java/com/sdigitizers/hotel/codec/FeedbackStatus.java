package com.sdigitizers.hotel.codec;

public enum FeedbackStatus {
	
	NA(0), GIVEN(1), NOTGIVEN(2);
	
	private int code;
	private FeedbackStatus(int id) {
		this.code = id;
	}
	
	public int getCode() {
		return code;
	}
	
	public static FeedbackStatus getValue(int code) {
		switch(code) {
		case 1 : return GIVEN;
		case 2 : return NOTGIVEN;
		
		default : return NA; 
		}
	}

}
