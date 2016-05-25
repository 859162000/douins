package com.douins.bank.domain.vo;

import java.io.Serializable;

public class BankTypeResponseVo implements Serializable {

	private static final long serialVersionUID = 1L;
	private String bankCode;
	private String bankName;
	private String everyLimit;// 每笔限额
	private String dailyLimit;// 每日限额
	private String note;

	public BankTypeResponseVo() {
	}

	public BankTypeResponseVo(String bankCode, String bankName,
			String everyLimit, String dailyLimit, String note) {
		this.bankCode = bankCode;
		this.bankName = bankName;
		this.dailyLimit = dailyLimit;
		this.everyLimit = everyLimit;
		this.note = note;
	}

	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getEveryLimit() {
		return everyLimit;
	}

	public void setEveryLimit(String everyLimit) {
		this.everyLimit = everyLimit;
	}

	public String getDailyLimit() {
		return dailyLimit;
	}

	public void setDailyLimit(String dailyLimit) {
		this.dailyLimit = dailyLimit;
	}

}
