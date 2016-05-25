/**
 * 
 */
package com.douins.bank.domain.model.nybc;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/** 
* @ClassName: VerifyIdentityResponse 
* @Description: 身份核查的响应数据
* @author G. F. 
* @date 2015年12月8日 上午10:29:26 
*  
*/
@XStreamAlias("SDOROOT")
public class VerifyIdentityResponse extends NYTransResponse {
    @XStreamAlias("BODY")
    private VerifyIdentityResponseBody body;
    
    /* (non-Javadoc)
     * @see com.douins.bank.domain.model.nybc.NYTransRequest#setBody(java.lang.Object)
     */
    @Override
    public <T> void setBody(T body) {
        this.body = (VerifyIdentityResponseBody)body;
    }

    public VerifyIdentityResponseBody getBody() {
        return body;
    }
}
