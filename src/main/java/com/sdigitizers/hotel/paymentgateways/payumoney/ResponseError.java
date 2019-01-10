package com.sdigitizers.hotel.paymentgateways.payumoney;

public class ResponseError {
   
    private final String errorMessage;
 
  public ResponseError(String message, String... args) {
    this.errorMessage = String.format(message, args);
  }
 
  public ResponseError(Exception e) {
    this.errorMessage = e.getMessage();
  }
 
  public String getMessage() {
    return this.errorMessage;
  }
 
  
}
