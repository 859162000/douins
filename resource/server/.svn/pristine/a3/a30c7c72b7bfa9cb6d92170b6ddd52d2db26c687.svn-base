/**
 * 
 */
package com.douins.bank.domain.model.nybc;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/** 
* @ClassName: RegistAccountResponse 
* @Description: 资金账户开户的响应数据
* @author G. F. 
* @date 2015年12月8日 下午2:54:43 
*  
*/
@XStreamAlias("SDOROOT")
public class RegistAccountResponse extends NYTransResponse {
    @XStreamAlias("BODY")
    private RegistAccountResponseBody body;
    
    /* (non-Javadoc)
     * @see com.douins.bank.domain.model.nybc.NYTransResponse#setBody(java.lang.Object)
     */
    @Override
    public <T> void setBody(T body) {
        this.body = (RegistAccountResponseBody)body;
    }

    public RegistAccountResponseBody getBody() {
        return body;
    }

}
