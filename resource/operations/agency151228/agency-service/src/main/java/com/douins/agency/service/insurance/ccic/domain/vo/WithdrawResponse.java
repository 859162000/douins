/**
 * 
 */
package com.douins.agency.service.insurance.ccic.domain.vo;

import com.douins.agency.service.insurance.ccic.domain.model.Endorse;
import com.douins.agency.service.insurance.ccic.domain.model.Policy;
import com.douins.agency.service.insurance.ccic.domain.model.ResponseStatus;
import com.thoughtworks.xstream.annotations.XStreamAlias;

/** 
* @ClassName: WithdrawResponse 
* @Description: CCIC 退保／注销返回的主数据结构
* @author G. F. 
* @date 2016年1月10日 下午4:40:33 
*  
*/
public class WithdrawResponse {
    private ResponseStatus responseStatus;
    private Endorse endorse;
    @XStreamAlias("main")
    private Policy policy;
    
    public ResponseStatus getResponseStatus() {
        return responseStatus;
    }
    public void setResponseStatus(ResponseStatus responseStatus) {
        this.responseStatus = responseStatus;
    }
    public Endorse getEndorse() {
        return endorse;
    }
    public void setEndorse(Endorse endorse) {
        this.endorse = endorse;
    }
    public Policy getPolicy() {
        return policy;
    }
    public void setPolicy(Policy policy) {
        this.policy = policy;
    }
}
