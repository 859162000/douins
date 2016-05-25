/**
 * 
 */
package com.douins.bank.domain.model.nybc;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/** 
* @ClassName: QueryAccountResponse 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author G. F. 
* @date 2015年12月9日 下午2:30:21 
*  
*/
@XStreamAlias("SDOROOT")
public class QueryAccountResponse extends NYTransResponse {
    @XStreamAlias("BODY")
    private QueryAccountResponseBody body;
    
    /* (non-Javadoc)
     * @see com.douins.bank.domain.model.nybc.NYTransResponse#setBody(java.lang.Object)
     */
    @Override
    public <T> void setBody(T body) {
        this.body = (QueryAccountResponseBody)body;
    }

    public QueryAccountResponseBody getBody() {
        return body;
    }

}
