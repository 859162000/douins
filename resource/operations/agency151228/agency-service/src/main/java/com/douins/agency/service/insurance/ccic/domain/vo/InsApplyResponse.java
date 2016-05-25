/**
 * 
 */
package com.douins.agency.service.insurance.ccic.domain.vo;

import com.douins.agency.service.insurance.ccic.domain.model.Applicant;
import com.douins.agency.service.insurance.ccic.domain.model.Insured;
import com.douins.agency.service.insurance.ccic.domain.model.Policy;
import com.douins.agency.service.insurance.ccic.domain.model.ResponseExpand;
import com.douins.agency.service.insurance.ccic.domain.model.ResponseStatus;
import com.thoughtworks.xstream.annotations.XStreamAlias;

/** 
* @ClassName: InsApplyResponse 
* @Description: CCIC 承保返回的数据
* @author G. F. 
* @date 2016年1月5日 下午9:47:53 
*  
*/
@XStreamAlias("root")
public class InsApplyResponse {
    private ResponseStatus responseStatus;
    @XStreamAlias("main")
    private Policy policy;
    private ResponseExpand expand;
    
    @XStreamAlias("reapplicant")
    private Applicant applicant;
    
    @XStreamAlias("reinsured")
    private Insured insured;
    
    private String orderNo;
    private String applySeq;
    private String tradeNo;
    
    
    
    public Applicant getApplicant() {
		return applicant;
	}
	public Insured getInsured() {
		return insured;
	}
	public void setApplicant(Applicant applicant) {
		this.applicant = applicant;
	}
	public void setInsured(Insured insured) {
		this.insured = insured;
	}
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
    public ResponseExpand getExpand() {
        return expand;
    }
    public void setExpand(ResponseExpand expand) {
        this.expand = expand;
    }
    public String getOrderNo() {
        return orderNo;
    }
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }
    public String getApplySeq() {
        return applySeq;
    }
    public void setApplySeq(String applySeq) {
        this.applySeq = applySeq;
    }
    public String getTradeNo() {
        return tradeNo;
    }
    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }
}
