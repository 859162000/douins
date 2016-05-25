package com.mango.api.userPayAccountAPI.vo;

import java.util.List;

import com.mango.api.basic.vo.ResponseTransVo;

public class UserPayAccountResponse extends ResponseTransVo {
	private static final long serialVersionUID = 1L;
	private List<UserPayAccountVo> accountList;

	public List<UserPayAccountVo> getAccountList() {
		return accountList;
	}

	public void setAccountList(List<UserPayAccountVo> accountList) {
		this.accountList = accountList;
	}

}
