package com.mango.fortune.insurance.pflife.vo;

public class ProposalResponse {
	private String orderId;
	private String proposalNo;
	private String totalPremium;
	private String underwriteFlag;
	private String failReason;
	
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
	public String getTotalPremium() {
		return totalPremium;
	}
	public void setTotalPremium(String totalPremium) {
		this.totalPremium = totalPremium;
	}
	public String getUnderwriteFlag() {
		return underwriteFlag;
	}
	public void setUnderwriteFlag(String underwriteFlag) {
		this.underwriteFlag = underwriteFlag;
	}
	public String getFailReason() {
		return failReason;
	}
	public void setFailReason(String failReason) {
		this.failReason = failReason;
	}

}
