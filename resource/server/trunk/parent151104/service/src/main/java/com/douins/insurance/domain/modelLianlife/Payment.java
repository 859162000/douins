package com.douins.insurance.domain.modelLianlife;

import com.thoughtworks.xstream.annotations.XStreamAlias;

public class Payment {
	@XStreamAlias("AccountDate")
	private String accountdate;
	@XStreamAlias("BankSerial")
	private String bankserial;
	@XStreamAlias("PayBankAcount")
	private String payBankAcount;
	@XStreamAlias("PayBankCode")
	private String paybankcode;
	@XStreamAlias("PayMoney")
	private String paymoney;
	@XStreamAlias("PayTime")
	private String paytime;
	@XStreamAlias("PayType")
	private String paytype;
	@XStreamAlias("PaymentId")
	private String paymentid;
	@XStreamAlias("ReciveBankAcount")
	private String recivebankacount;
	@XStreamAlias("ReciveBankCode")
	private String recivebankcode;
	@XStreamAlias("ReciveBankUserId")
	private String recivebankuserid;
	@XStreamAlias("ReciveBankUserName")
	private String recivebankusename;

	
	public String getAccountdate() {
		return accountdate;
	}
	public void setAccountdate(String accountdate) {
		this.accountdate = accountdate;
	}
	public String getBankserial() {
		return bankserial;
	}
	public void setBankserial(String bankserial) {
		this.bankserial = bankserial;
	}
	public String getPaybankcode() {
		return paybankcode;
	}
	public void setPaybankcode(String paybankcode) {
		this.paybankcode = paybankcode;
	}
	public String getPaymoney() {
		return paymoney;
	}
	public void setPaymoney(String paymoney) {
		this.paymoney = paymoney;
	}
	public String getPaytime() {
		return paytime;
	}
	public void setPaytime(String paytime) {
		this.paytime = paytime;
	}
	public String getPaytype() {
		return paytype;
	}
	public void setPaytype(String paytype) {
		this.paytype = paytype;
	}
	public String getPaymentid() {
		return paymentid;
	}
	public void setPaymentid(String paymentid) {
		this.paymentid = paymentid;
	}
	public String getRecivebankacount() {
		return recivebankacount;
	}
	public void setRecivebankacount(String recivebankacount) {
		this.recivebankacount = recivebankacount;
	}
	public String getRecivebankcode() {
		return recivebankcode;
	}
	public void setRecivebankcode(String recivebankcode) {
		this.recivebankcode = recivebankcode;
	}
	public String getRecivebankuserid() {
		return recivebankuserid;
	}
	public void setRecivebankuserid(String recivebankuserid) {
		this.recivebankuserid = recivebankuserid;
	}
	public String getRecivebankusename() {
		return recivebankusename;
	}
	public void setRecivebankusename(String recivebankusename) {
		this.recivebankusename = recivebankusename;
	}
	public String getPayBankAcount() {
		return payBankAcount;
	}
	public void setPayBankAcount(String payBankAcount) {
		this.payBankAcount = payBankAcount;
	}
	

	
}
