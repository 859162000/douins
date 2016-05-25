/**
 * 
 */
package com.douins.bank.domain.model.nybc;

import com.douins.bank.domain.model.BankResponse;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;

/** 
* @ClassName: ResponseData 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author G. F. 
* @date 2015年12月5日 下午10:54:18 
*  
*/
public class VerifyIdentityResponseBody extends BankResponse {
  
    @XStreamAlias("CHECK_FLAG")
    private String checkFlag;

    public String getCheckFlag() {
        return checkFlag;
    }

    public void setCheckFlag(String checkFlag) {
        this.checkFlag = checkFlag;
    }
    
    
    public static void main(String[] args){
        VerifyIdentityResponseBody response = new VerifyIdentityResponseBody();
        response.setCheckFlag("0");
        response.setCode("faetretjret80937");
        response.setMessage("just a test");
        
        XStream xStream = new XStream();
        xStream.processAnnotations(VerifyIdentityResponseBody.class);
        xStream.aliasField("ERROR_CODE", VerifyIdentityResponseBody.class, "code");
        xStream.aliasField("ERROR_MSG", VerifyIdentityResponseBody.class, "message");
        String result = xStream.toXML(response);
        System.out.println(result);
    }
}
