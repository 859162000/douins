package com.mango.api.userAccountAPI.vo;

import com.mango.api.basic.vo.ResponseTransVo;

public class UserAccountResponse extends ResponseTransVo {
	private static final long serialVersionUID = 6708038998962270094L;
	private UserAccountResponseVo userAccount;
	public UserAccountResponseVo getUserAccount() {
		return userAccount;
	}
	public void setUserAccount(UserAccountResponseVo userAccount) {
		this.userAccount = userAccount;
	}

}
