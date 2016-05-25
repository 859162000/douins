/**
 * 
 */
package com.douins.bank.domain.model.nybc;

import com.douins.account.domain.model.User;
import com.thoughtworks.xstream.annotations.XStreamAlias;

/** 
* @ClassName: VerifyIdentityRequest 
* @Description: 身份核查 
* @author G. F. 
* @date 2015年12月6日 上午12:34:00 
*  
*/
public class VerifyIdentityRequestBody{
    
    @XStreamAlias("CERT_TYPE")
    private String certtType;                   // 证件类型
    @XStreamAlias("CERT_NO")
    private String certCode;                    // 证件号码
    @XStreamAlias("CUST_NAME")
    private String userName;                    // 用户姓名
    

    public VerifyIdentityRequestBody(User user){
        this.setCertCode(user.getCertiCode());
        this.setCerttType(user.getCertiType());
        this.setUserName(user.getUserName());
    }
    
    
    public VerifyIdentityRequestBody() {
        // TODO Auto-generated constructor stub
    }

    public String getCerttType() {
        return certtType;
    }


    public void setCerttType(String certtType) {
        this.certtType = certtType;
    }


    public String getCertCode() {
        return certCode;
    }


    public void setCertCode(String certCode) {
        this.certCode = certCode;
    }


    public String getUserName() {
        return userName;
    }


    public void setUserName(String userName) {
        this.userName = userName;
    }
}
