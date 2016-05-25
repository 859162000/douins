/**
 * Copyright (c) 2015 www.sinorfc.com All Rights Reserved.  
 */
package com.douins.account.domain.vo;

import com.douins.trans.domain.vo.ResponseTransVo;

/**
 * @author xuhuiwang
 * @version 1.0, 2015年4月28日 上午9:47:48   
 */
public class UserResponse extends ResponseTransVo{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2813313745971023541L;
	private UserResponseVo user;
	
	public UserResponse(){}
	public UserResponse(boolean fair){
		if(fair){
			this.user=new UserResponseVo();
		}
	}
	public UserResponseVo getUser() {
		return user;
	}
	public void setUser(UserResponseVo user) {
		this.user = user;
	}
	

}
