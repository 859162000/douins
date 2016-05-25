package com.douins.account.domain.vo;

import java.io.Serializable;




/**
 * 绑定卡号 VO
 * @author shaotongyao
 *
 */

public class BindCardRequestVO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String validateCode;// 验证码
	
	private String bindPhone;//绑定手机号
	
	private String  accountNo; //银行卡号
	

	public String getValidateCode() {
		return validateCode;
	}
	
	public void setValidateCode(String validateCode) {
		this.validateCode = validateCode;
	}
	

	public String getBindPhone() {
		return bindPhone;
	}

	public void setBindPhone(String bindPhone) {
		this.bindPhone = bindPhone;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	
	

}
