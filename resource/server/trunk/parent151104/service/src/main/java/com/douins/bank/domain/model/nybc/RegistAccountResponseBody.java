/**
 * 
 */
package com.douins.bank.domain.model.nybc;

import com.douins.bank.domain.model.BankResponse;
import com.thoughtworks.xstream.annotations.XStreamAlias;

/** 
* @ClassName: RegistAccountResponseBody 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author G. F. 
* @date 2015年12月8日 下午2:56:29 
*  
*/
public class RegistAccountResponseBody extends BankResponse {
    @XStreamAlias("ACCT_NO")
    private String accountNo;               // 电子账号
    @XStreamAlias("ACCT_NO_ID")
    private String accountId;               // 电子账户在银行的 ID
    @XStreamAlias("USER_FLAG")
    private String userStatus;              // 用户账户状态
    
    public String getAccountNo() {
        return accountNo;
    }
    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }
    public String getAccountId() {
        return accountId;
    }
    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }
    public String getUserStatus() {
        return userStatus;
    }
    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }
}

