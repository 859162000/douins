/**
 * Copyright (c) 2015 www.sinorfc.com All Rights Reserved.  
 */
package com.douins.account.domain.vo;

import com.douins.trans.domain.vo.RequestTransVo;

/**
 * @author xuhuiwang
 * @version 1.0, 2015年4月29日 上午10:06:01   
 */
public class UserRequestTrans extends RequestTransVo {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8446979800414262564L;
	private UserRequestVo userVo;
	public UserRequestVo getUserVo() {
		return userVo;
	}
	public void setUserVo(UserRequestVo userVo) {
		this.userVo = userVo;
	}


	
	
}
