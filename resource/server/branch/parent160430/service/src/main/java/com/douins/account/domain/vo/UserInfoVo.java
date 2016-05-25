/**
 * 
 */
package com.douins.account.domain.vo;

import java.math.BigDecimal;

import com.douins.account.domain.model.User;
import com.douins.account.domain.model.UserAccount;

/** 
* @ClassName: UserInfoVo 
* @Description: 用户信息
* @author G. F. 
* @date 2015年11月30日 下午4:00:07 
*  
*/
public class UserInfoVo  {
    public  User user;
    public UserAccount account;
    
    public UserInfoVo(User user, UserAccount account){
        this.user = user;
        this.account = account;
    }

    public String getProvince() {
        return user.getProvince();
    }

    public String getCity() {
        return user.getCity();
    }

    public String getDistrict() {
        return user.getDistrict();
    }

    public String getDetailedAdress() {
        return user.getDetailedAdress();
    }

    public BigDecimal getAccountBalance() {
        return account.getAccountBalance();
    }

	public User getUser() {
		return user;
	}

	public UserAccount getAccount() {
		return account;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setAccount(UserAccount account) {
		this.account = account;
	}

	@Override
	public String toString() {
		return "UserInfoVo [user=" + user + ", account=" + account + "]";
	}
    
}
