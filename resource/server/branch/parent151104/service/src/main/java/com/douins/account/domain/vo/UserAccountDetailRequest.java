package com.douins.account.domain.vo;

import com.douins.trans.domain.vo.RequestTransVo;

public class UserAccountDetailRequest extends RequestTransVo {
	
	private static final long serialVersionUID = 380955690652064222L;
	private UserAccountDetailRequestVo userAccountDetailVo;
	public UserAccountDetailRequestVo getUserAccountDetailVo() {
		return userAccountDetailVo;
	}
	public void setUserAccountDetailVo(
			UserAccountDetailRequestVo userAccountDetailVo) {
		this.userAccountDetailVo = userAccountDetailVo;
	}
	@Override
	public String toString() {
		return "UserAccountDetailRequest [userAccountDetailVo="
				+ userAccountDetailVo + "]";
	}

}
