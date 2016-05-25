/**
 * 
 */
package com.douins.bank.domain.model.nybc;

import com.douins.account.domain.model.User;
import com.thoughtworks.xstream.annotations.XStreamAlias;

/** 
* @ClassName: QueryAccountRequestBody 
* @Description: 查询账户信息
* @author G. F. 
* @date 2015年12月7日 下午7:42:37 
*  
*/
public class QueryAccountRequestBody {
    @XStreamAlias("CERT_NO")
    private String certiCode;
    
    public QueryAccountRequestBody(){
        
    }
    
    public QueryAccountRequestBody(User user){
        this.setCertiCode(user.getCertiCode());
    }

    public String getCertiCode() {
        return certiCode;
    }

    public void setCertiCode(String certiCode) {
        this.certiCode = certiCode;
    }
}
