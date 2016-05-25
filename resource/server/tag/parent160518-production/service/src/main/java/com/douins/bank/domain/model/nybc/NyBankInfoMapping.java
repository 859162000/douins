package com.douins.bank.domain.model.nybc;

import com.douins.common.domain.BaseModel;

public class NyBankInfoMapping extends BaseModel{

	private String paymentBankName;
	private String frontCardNumber;
	private String frontCardNumberLength;
	private String cardLength;
	private String binStatus;
	private String cardName;
	private String cardType;
	private String binCode;
	private String contactNumber;
	public String getPaymentBankName() {
		return paymentBankName;
	}
	public void setPaymentBankName(String paymentBankName) {
		this.paymentBankName = paymentBankName;
	}
	public String getFrontCardNumber() {
		return frontCardNumber;
	}
	public void setFrontCardNumber(String frontCardNumber) {
		this.frontCardNumber = frontCardNumber;
	}
	public String getFrontCardNumberLength() {
		return frontCardNumberLength;
	}
	public void setFrontCardNumberLength(String frontCardNumberLength) {
		this.frontCardNumberLength = frontCardNumberLength;
	}
	public String getCardLength() {
		return cardLength;
	}
	public void setCardLength(String cardLength) {
		this.cardLength = cardLength;
	}
	public String getBinStatus() {
		return binStatus;
	}
	public void setBinStatus(String binStatus) {
		this.binStatus = binStatus;
	}
	public String getCardName() {
		return cardName;
	}
	public void setCardName(String cardName) {
		this.cardName = cardName;
	}
	public String getCardType() {
		return cardType;
	}
	public void setCardType(String cardType) {
		this.cardType = cardType;
	}
	public String getBinCode() {
		return binCode;
	}
	public void setBinCode(String binCode) {
		this.binCode = binCode;
	}
	public String getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	
	
	

}
