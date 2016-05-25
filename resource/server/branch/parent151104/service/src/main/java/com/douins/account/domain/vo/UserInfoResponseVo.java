/**
 * 
 */
package com.douins.account.domain.vo;

import com.douins.trans.domain.vo.ResponseTransVo;

/** 
* @ClassName: UserInfoResponseVo 
* @Description: 用户信息响应对象
* @author G. F. 
* @date 2015年11月30日 下午4:18:20 
*  
*/
public class UserInfoResponseVo extends ResponseTransVo {
    private static final long serialVersionUID = 1542327865443203251L;

    private UserInfoVo userInfoVo;

    public UserInfoVo getUserInfoVo() {
        return userInfoVo;
    }

    public void setUserInfoVo(UserInfoVo userInfoVo) {
        this.userInfoVo = userInfoVo;
    }

	@Override
	public String toString() {
		return "UserInfoResponseVo [userInfoVo=" + userInfoVo + "]";
	}
    
    
    
}
