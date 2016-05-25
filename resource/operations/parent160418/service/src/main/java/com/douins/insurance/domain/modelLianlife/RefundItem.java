package com.douins.insurance.domain.modelLianlife;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("RefundItem")
public class RefundItem {
	@XStreamAlias("PolicyNo")
	private String policyno;
	@XStreamAlias("RefundTime")
	private String refundtime;
	@XStreamAlias("ApplicantName")
	private String applicantName;
	public String getPolicyno() {
		return policyno;
	}
	public void setPolicyno(String policyno) {
		this.policyno = policyno;
	}
	
	public String getApplicantName() {
		return applicantName;
	}
	public void setApplicantName(String applicantName) {
		this.applicantName = applicantName;
	}
	public String getRefundtime() {
		return refundtime;
	}
	public void setRefundtime(String refundtime) {
		this.refundtime = refundtime;
	}
	
	
}
