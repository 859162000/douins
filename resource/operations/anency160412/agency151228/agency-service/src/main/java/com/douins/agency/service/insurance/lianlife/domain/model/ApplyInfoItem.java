package com.douins.agency.service.insurance.lianlife.domain.model;

import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("applyinfoitem")
public class ApplyInfoItem {
	private List<ApplyNoItem> applynolist;
	private String issuccess,
		orderid,
		totalpremium,
		accountdate,
		applicantname,
		failreason;
	
	public String getFailreason() {
		return failreason;
	}

	public void setFailreason(String failreason) {
		this.failreason = failreason;
	}

	public List<ApplyNoItem> getapplynolist() {
		return applynolist;
	}

	public void setapplynolist(List<ApplyNoItem> applynolist) {
		this.applynolist = applynolist;
	}

	public String getissuccess() {
		return issuccess;
	}

	public void setissuccess(String issuccess) {
		this.issuccess = issuccess;
	}

	public String getorderid() {
		return orderid;
	}

	public void setorderid(String orderid) {
		this.orderid = orderid;
	}

	public String gettotalpremium() {
		return totalpremium;
	}

	public void settotalpremium(String totalpremium) {
		this.totalpremium = totalpremium;
	}

	public String getaccountdate() {
		return accountdate;
	}

	public void setaccountdate(String accountdate) {
		this.accountdate = accountdate;
	}

	public String getapplicantname() {
		return applicantname;
	}

	public void setapplicantname(String applicantname) {
		this.applicantname = applicantname;
	} 
	
	
}
