package com.sdigitizers.hotel.model;

import javax.persistence.Embeddable;

@Embeddable
public class BankDetails {
	
	private String bankName;
    private String accountName;
    private String accountNumber;
    private String IFSC;
    private String branchName;
    private String UPIAddress;
    private String other;
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getIFSC() {
		return IFSC;
	}
	public void setIFSC(String iFSC) {
		IFSC = iFSC;
	}
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	public String getUPIAddress() {
		return UPIAddress;
	}
	public void setUPIAddress(String uPIAddress) {
		UPIAddress = uPIAddress;
	}
	public String getOther() {
		return other;
	}
	public void setOther(String other) {
		this.other = other;
	}
    
    

}
