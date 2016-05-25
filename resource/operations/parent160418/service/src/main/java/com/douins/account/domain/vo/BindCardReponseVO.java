package com.douins.account.domain.vo;

import java.io.Serializable;

import javax.validation.constraints.NotNull;




/**
 * 绑定卡号 VO
 * @author shaotongyao
 *
 */

public class BindCardReponseVO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String nyBindCardUrl;//南粤绑卡URL
	private String nyUnbindCardUrl;//南粤解绑 URL
	private String bankName;  //银行名称
	private String  accountNo; //银行卡号

	
	
	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public String getNyBindCardUrl() {
		return nyBindCardUrl;
	}

	public void setNyBindCardUrl(String nyBindCardUrl) {
		this.nyBindCardUrl = nyBindCardUrl;
	}

	public String getNyUnbindCardUrl() {
		return nyUnbindCardUrl;
	}

	public void setNyUnbindCardUrl(String nyUnbindCardUrl) {
		this.nyUnbindCardUrl = nyUnbindCardUrl;
	}


	

}
