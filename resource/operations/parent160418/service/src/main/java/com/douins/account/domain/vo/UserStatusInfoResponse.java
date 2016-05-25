/**
 * 
 */
package com.douins.account.domain.vo;

import com.douins.trans.domain.vo.ResponseTransVo;

/** 
* @ClassName: UserStatusInfoResponse 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author G. F. 
* @date 2015年12月3日 下午5:31:57 
*  
*/
public class UserStatusInfoResponse extends ResponseTransVo {
    private static final long serialVersionUID = 3590384848260244200L;
    
    private UserStatusInfoVo userStatus;

    public UserStatusInfoVo getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(UserStatusInfoVo userStatus) {
        this.userStatus = userStatus;
    }
}
