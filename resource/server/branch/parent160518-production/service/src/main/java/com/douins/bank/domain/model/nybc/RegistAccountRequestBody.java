/**
 * 
 */
package com.douins.bank.domain.model.nybc;

import com.douins.account.domain.model.User;
import com.douins.common.util.ReadConfig;
import com.thoughtworks.xstream.annotations.XStreamAlias;

/** 
* @ClassName: RegistAccountRequestBody 
* @Description: 注册资金账户的请求数据模型
* @author G. F. 
* @date 2015年12月7日 下午5:24:52 
*  
*/
public class RegistAccountRequestBody{
    @XStreamAlias("CERT_TYPE")
    private String certiType;           // 证件类型
    @XStreamAlias("CERT_NO")
    private String certiCode;           // 证件号码
    @XStreamAlias("USR_NAME")
    private String userName;        // 用户名称
    @XStreamAlias("MOBILE")
    private String mobile;              // 手机号
    @XStreamAlias("ACCESS_IP")
    private String ip;
    @XStreamAlias("ACCESS_MAC")
    private String mac;
    
    public RegistAccountRequestBody(){
        
    }
    
    public RegistAccountRequestBody(User user){
        this.setUserName(user.getUserName());
        this.setCertiCode(user.getCertiCode());
        this.setCertiType(user.getCertiType());
        this.setMobile(user.getUserMobile());
        
        this.setMac(ReadConfig.get("macAddress"));
        this.setIp(user.getClientIp());
    }
    
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getCertiType() {
        return certiType;
    }
    public void setCertiType(String certiType) {
        this.certiType = certiType;
    }
    public String getCertiCode() {
        return certiCode;
    }
    public void setCertiCode(String certiCode) {
        this.certiCode = certiCode;
    }
    public String getMobile() {
        return mobile;
    }
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    public String getIp() {
        return ip;
    }
    public void setIp(String ip) {
        this.ip = ip;
    }
    public String getMac() {
        return mac;
    }
    public void setMac(String mac) {
        this.mac = mac;
    }
}
