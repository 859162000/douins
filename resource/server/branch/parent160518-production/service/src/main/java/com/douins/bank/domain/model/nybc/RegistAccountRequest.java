/**
 * 
 */
package com.douins.bank.domain.model.nybc;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/** 
* @ClassName: RegistAccountRequest 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author G. F. 
* @date 2015年12月7日 下午5:31:43 
*  
*/
@XStreamAlias("SDOROOT")
public class RegistAccountRequest extends NYTransRequest {

    @XStreamAlias("BODY")
    private RegistAccountRequestBody body;
    
    /* (non-Javadoc)
     * @see com.douins.bank.domain.model.nybc.NYTransRequest#setBody(java.lang.Object)
     */
    @Override
    public <T> void setBody(T body) {
        this.body = (RegistAccountRequestBody)body;
    }

    public RegistAccountRequestBody getBody() {
        return body;
    }

}
