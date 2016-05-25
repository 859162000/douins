package com.douins.agency.service.insurance.lianlife.domain.model;

import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("policyinfoitem")
public class PolicyInfoItem {
	
	private String issuccess,
		orderid,
		policyno,
		idno,
		totalpremium,
		accountdate,
		applicantname,
		applyno,
		failreason;
	private List<PolicyNoItem> policynolist;
	
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

	public List<PolicyNoItem> getPolicynolist() {
		return policynolist;
	}

	public void setPolicynolist(List<PolicyNoItem> policynolist) {
		this.policynolist = policynolist;
	}

	public String getApplicantname() {
		return applicantname;
	}

	public void setApplicantname(String applicantname) {
		this.applicantname = applicantname;
	}

	public String getApplyno() {
		return applyno;
	}

	public String getFailreason() {
		return failreason;
	}

	public void setApplyno(String applyno) {
		this.applyno = applyno;
	}

	public void setFailreason(String failreason) {
		this.failreason = failreason;
	}
	
}
