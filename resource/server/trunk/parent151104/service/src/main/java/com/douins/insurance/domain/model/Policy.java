package com.douins.insurance.domain.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;

public class Policy {
    @XStreamAlias("OrderId")
	private String orderId;
	private String proposalNo;
	private String policyNo;
	@XStreamAlias("TotalPremium")
	private String totalPremium;
	private String isSuccess;//1 成功
	private String failReason;
	private String policyUrl;
	
	@XStreamAlias("item")
	private PolicyInfo policyInfo;
	
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getProposalNo() {
		return proposalNo;
	}
	public void setProposalNo(String proposalNo) {
		this.proposalNo = proposalNo;
	}
	public String getPolicyNo() {
		return policyNo;
	}
	public void setPolicyNo(String policyNo) {
		this.policyNo = policyNo;
	}
	public String getTotalPremium() {
		return totalPremium;
	}
	public void setTotalPremium(String totalPremium) {
		this.totalPremium = totalPremium;
	}
	public String getIsSuccess() {
		return isSuccess;
	}
	public void setIsSuccess(String isSuccess) {
		this.isSuccess = isSuccess;
	}
	public String getFailReason() {
		return failReason;
	}
	public void setFailReason(String failReason) {
		this.failReason = failReason;
	}
	public String getPolicyUrl() {
		return policyUrl;
	}
	public void setPolicyUrl(String policyUrl) {
		this.policyUrl = policyUrl;
	}
    public PolicyInfo getPolicyInfo() {
        return policyInfo;
    }
    public void setPolicyInfo(PolicyInfo policyInfo) {
        this.policyInfo = policyInfo;
    }
}
