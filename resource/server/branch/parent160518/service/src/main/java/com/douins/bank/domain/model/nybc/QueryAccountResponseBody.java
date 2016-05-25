/**
 * 
 */
package com.douins.bank.domain.model.nybc;

import com.douins.bank.domain.model.BankResponse;
import com.thoughtworks.xstream.annotations.XStreamAlias;

/** 
* @ClassName: QueryAccountResponseBody 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author G. F. 
* @date 2015年12月9日 下午2:17:07 
*  
*/
public class QueryAccountResponseBody extends BankResponse {
    @XStreamAlias("ACTIVE_FLAG")
    private String activeFlag;      // 账户是否激活的标识
    @XStreamAlias("PWD_FLAG")
    private String pwdFlag;         // 交易密码是否已设置的标识
    @XStreamAlias("STOP_FLAG")
    private String stopFlag;            // 止付、解止付的标识
    @XStreamAlias("VAL_TOTAL_BAL")
    private String totalBalance;        // 账户总资金
    @XStreamAlias("VAL_ACCT_BAL")
    private String accBalance;          // 可用总资金
    
    public String getActiveFlag() {
        return activeFlag;
    }
    public void setActiveFlag(String activeFlag) {
        this.activeFlag = activeFlag;
    }
    public String getPwdFlag() {
        return pwdFlag;
    }
    public void setPwdFlag(String pwdFlag) {
        this.pwdFlag = pwdFlag;
    }
    public String getStopFlag() {
        return stopFlag;
    }
    public void setStopFlag(String stopFlag) {
        this.stopFlag = stopFlag;
    }
    public String getTotalBalance() {
        return totalBalance;
    }
    public void setTotalBalance(String totalBalance) {
        this.totalBalance = totalBalance;
    }
    public String getAccBalance() {
        return accBalance;
    }
    public void setAccBalance(String accBalance) {
        this.accBalance = accBalance;
    }
  
}
