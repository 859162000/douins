package com.douins.insurance.domain.modelLianlife;

import com.thoughtworks.xstream.annotations.XStreamAlias;

public class PolicyInfoItem {
	@XStreamAlias("IsSuccess")
	private String issuccess;
	@XStreamAlias("OrderID")
	private String orderid;
	@XStreamAlias("PolicyNo")
	private String policyno;
	@XStreamAlias("PolicyNoList")
	private PolicyNoList policyNoList;
	@XStreamAlias("IDNO")
	private String idno;
	@XStreamAlias("TotalPremium")
	private String totalpremium;
	@XStreamAlias("AccountDate")
	private String accountdate;
	@XStreamAlias("ApplicantName")
	private String applicantName;
	@XStreamAlias("FailReason")
	private String failReason;
	public String getIssuccess() {
		return issuccess;
	}
	public void setIssuccess(String issuccess) {
		this.issuccess = issuccess;
	}
	public String getOrderid() {
		return orderid;
	}
	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}
	public String getPolicyno() {
		return policyno;
	}
	public void setPolicyno(String policyno) {
		this.policyno = policyno;
	}

	public PolicyNoList getPolicyNoList() {
		return policyNoList;
	}
	public void setPolicyNoList(PolicyNoList policyNoList) {
		this.policyNoList = policyNoList;
	}
	public String getIdno() {
		return idno;
	}
	public void setIdno(String idno) {
		this.idno = idno;
	}
	public String getTotalpremium() {
		return totalpremium;
	}
	public void setTotalpremium(String totalpremium) {
		this.totalpremium = totalpremium;
	}
	public String getAccountdate() {
		return accountdate;
	}
	public void setAccountdate(String accountdate) {
		this.accountdate = accountdate;
	}
	public String getFailReason() {
		return failReason;
	}
	public void setFailReason(String failReason) {
		this.failReason = failReason;
	}
	public String getApplicantName() {
		return applicantName;
	}
	public void setApplicantName(String applicantName) {
		this.applicantName = applicantName;
	}
	
	
}
