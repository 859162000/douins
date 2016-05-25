/**
 * 
 */
package com.douins.agency.service.insurance.ccic.domain.vo;

import com.douins.agency.service.insurance.ccic.domain.model.Policy;
import com.douins.agency.service.insurance.ccic.domain.model.ResponseStatus;
import com.thoughtworks.xstream.annotations.XStreamAlias;

/** 
* @ClassName: InsUWResponse 
* @Description: CCIC 核保返回
* @author G. F. 
* @date 2016年1月5日 下午9:34:17 
*  
*/
@XStreamAlias("root")
public class InsUWResponse {
    private ResponseStatus responseStatus;
    @XStreamAlias("main")
    private Policy policy;
    
    private String applySeq;
    
    public ResponseStatus getResponseStatus() {
        return responseStatus;
    }
    public void setResponseStatus(ResponseStatus responseStatus) {
        this.responseStatus = responseStatus;
    }
    public Policy getPolicy() {
        return policy;
    }
    public void setPolicy(Policy policy) {
        this.policy = policy;
    }
    public String getApplySeq() {
        return applySeq;
    }
    public void setApplySeq(String applySeq) {
        this.applySeq = applySeq;
    }
}
