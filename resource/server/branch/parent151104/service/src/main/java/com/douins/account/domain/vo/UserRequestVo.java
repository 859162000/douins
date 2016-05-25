/**
 * Copyright (c) 2015 www.sinorfc.com All Rights Reserved.  
 */
package com.douins.account.domain.vo;

import com.douins.account.domain.model.User;

/**
 * 描述：<br>
 * 作者：wintrchen <br>
 * 修改日期：May 5, 20151:43:35 PM <br>
 * E-mail: winterchen@sinorfc.com <br>
 */
public class UserRequestVo extends User {

	/**
	 * UserVo.java 
	 */
	private static final long serialVersionUID = 1L;
	private String verificationCode;//手机验证码
	private String oldLoginPassword;//原登陆密码
	private String oldPayPassword;//原支付密码
	private String oldGesturePassword;//原手势密码
	
	private String bankCardNumber;//用作支付密码找回
	
	
	
	public String getOldGesturePassword() {
		return oldGesturePassword;
	}
	public void setOldGesturePassword(String oldGesturePassword) {
		this.oldGesturePassword = oldGesturePassword;
	}
	public String getBankCardNumber() {
		return bankCardNumber;
	}
	public void setBankCardNumber(String bankCardNumber) {
		this.bankCardNumber = bankCardNumber;
	}
	public String getVerificationCode() {
		return verificationCode;
	}
	public void setVerificationCode(String verificationCode) {
		this.verificationCode = verificationCode;
	}
	public String getOldLoginPassword() {
		return oldLoginPassword;
	}
	public void setOldLoginPassword(String oldLoginPassword) {
		this.oldLoginPassword = oldLoginPassword;
	}
/*	public String getOldGesturePassword() {
		return oldGesturePassword;
	}
	public void setOldGesturePassword(String oldGesturePassword) {
		this.oldGesturePassword = oldGesturePassword;
	}*/
	public String getOldPayPassword() {
		return oldPayPassword;
	}
	public void setOldPayPassword(String oldPayPassword) {
		this.oldPayPassword = oldPayPassword;
	}
	
	
	
	
	
}
