/**
 * 
 */
package com.douins.account.domain.vo;

import com.douins.account.domain.model.User;
import com.douins.account.domain.model.UserAccount;
import com.douins.bank.domain.model.nybc.GateWayUrl;
import com.douins.bank.domain.vo.NYGateWayUrlH5;

/** 
* @ClassName: UserStatusInfoVo 
* @Description: 用户状态信息接口
* @author G. F. 
* @date 2015年12月3日 下午4:47:46 
*  
*/
public class UserStatusInfoVo {
    private User user;
    private UserAccount account;
    private NYGateWayUrlH5 gateWayUrl;
    
    public UserStatusInfoVo(User user, UserAccount account){
        this.user = user;
        this.account = account;
        gateWayUrl = new NYGateWayUrlH5();
    }
    
    public String getAccountStatus() {
        return account.getStatus();
    }
    public String getPasswordStatus() {
        return account.getPasswordStatus();
    }
    
    public String getUserStatus() {
        return user.getStatus();
    }

    public String getVirtualAccountId() {
        return account.getVirtualAccountId();
    }

//    public String getStatus() {
//        return account.getStatus();
//    }
    
    public int getMsgCount() {
        return user.getMsgCount();
    }

    public NYGateWayUrlH5 getGateWayUrl() {
        return gateWayUrl;
    }
}
