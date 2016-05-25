package com.douins.account.domain.vo;

import com.douins.account.domain.model.UserPayAccount;
import com.douins.trans.domain.vo.RequestTransVo;

public class UserPayAccountRequest  extends RequestTransVo {

	/**
	 * UserBankRequest.java 
	 */
	private static final long serialVersionUID = 1L;
	
	private UserPayAccount payAccountVo;

	public UserPayAccount getPayAccountVo() {
		return payAccountVo;
	}

	public void setPayAccountVo(UserPayAccount payAccountVo) {
		this.payAccountVo = payAccountVo;
	}
}
