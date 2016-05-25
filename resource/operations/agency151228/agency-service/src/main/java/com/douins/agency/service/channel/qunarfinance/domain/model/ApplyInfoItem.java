package com.douins.agency.service.channel.qunarfinance.domain.model;

import java.util.List;

public class ApplyInfoItem {
	private List<ApplyNoItem> applynolist;
	private String issuccess;
	private String orderid;
	private String totalpremium;
	private String accountdate;
	private String applicantname;
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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((accountdate == null) ? 0 : accountdate.hashCode());
		result = prime * result
				+ ((applicantname == null) ? 0 : applicantname.hashCode());
		result = prime * result
				+ ((applynolist == null) ? 0 : applynolist.hashCode());
		result = prime * result
				+ ((issuccess == null) ? 0 : issuccess.hashCode());
		result = prime * result + ((orderid == null) ? 0 : orderid.hashCode());
		result = prime * result
				+ ((totalpremium == null) ? 0 : totalpremium.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ApplyInfoItem other = (ApplyInfoItem) obj;
		if (accountdate == null) {
			if (other.accountdate != null)
				return false;
		} else if (!accountdate.equals(other.accountdate))
			return false;
		if (applicantname == null) {
			if (other.applicantname != null)
				return false;
		} else if (!applicantname.equals(other.applicantname))
			return false;
		if (applynolist == null) {
			if (other.applynolist != null)
				return false;
		} else if (!applynolist.equals(other.applynolist))
			return false;
		if (issuccess == null) {
			if (other.issuccess != null)
				return false;
		} else if (!issuccess.equals(other.issuccess))
			return false;
		if (orderid == null) {
			if (other.orderid != null)
				return false;
		} else if (!orderid.equals(other.orderid))
			return false;
		if (totalpremium == null) {
			if (other.totalpremium != null)
				return false;
		} else if (!totalpremium.equals(other.totalpremium))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "ApplyInfoItem [applynolist=" + applynolist + ", issuccess="
				+ issuccess + ", orderid=" + orderid + ", totalpremium="
				+ totalpremium + ", accountdate=" + accountdate
				+ ", applicantname=" + applicantname + "]";
	}
	
	
}
