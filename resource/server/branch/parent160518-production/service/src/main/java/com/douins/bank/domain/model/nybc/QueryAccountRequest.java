/**
 * 
 */
package com.douins.bank.domain.model.nybc;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/** 
* @ClassName: QueryAccountRequest 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author G. F. 
* @date 2015年12月7日 下午7:44:17 
*  
*/
@XStreamAlias("SDOROOT")
public class QueryAccountRequest extends NYTransRequest {
    @XStreamAlias("BODY")
    private QueryAccountRequestBody body;
    
    /* (non-Javadoc)
     * @see com.douins.bank.domain.model.nybc.NYTransRequest#setBody(java.lang.Object)
     */
    @Override
    public <T> void setBody(T body) {
        this.body = (QueryAccountRequestBody)body;
    }

    public QueryAccountRequestBody getBody() {
        return body;
    }

}
