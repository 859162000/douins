package com.douins.account.domain.vo;

import com.douins.trans.domain.vo.RequestTransVo;

public class UserAccountRequest extends RequestTransVo {
	
	private static final long serialVersionUID = 380955690652064222L;
	private UserAccountRequestVo userAccountVo;
	public UserAccountRequestVo getUserAccountVo() {
		return userAccountVo;
	}
	public void setUserAccountVo(UserAccountRequestVo userAccountVo) {
		this.userAccountVo = userAccountVo;
	}

}
