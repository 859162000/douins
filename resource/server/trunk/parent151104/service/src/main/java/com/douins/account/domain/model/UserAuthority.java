/**
 * 
 */
package com.douins.account.domain.model;

import java.util.Date;

import com.douins.common.domain.BaseModel;

/** 
* @ClassName: UserAuthority 
* @Description: 用户权限
* @author G. F. 
* @date 2015年11月18日 下午4:40:25 
*  
*/
public class UserAuthority extends BaseModel{
    
    private long id;
    private String uid;     // 用户 ID
    private String token;       // 访问接口的身份令牌
    private Date expiredTime;       // 令牌过期时间
    
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getUid() {
        return uid;
    }
    public void setUid(String uid) {
        this.uid = uid;
    }
    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }
    public Date getExpiredTime() {
        return expiredTime;
    }
    public void setExpiredTime(Date expiredTime) {
        this.expiredTime = expiredTime;
    }
	@Override
	public String toString() {
		return "UserAuthority [id=" + id + ", uid=" + uid + ", token=" + token
				+ ", expiredTime=" + expiredTime + "]";
	}
    
    
}
