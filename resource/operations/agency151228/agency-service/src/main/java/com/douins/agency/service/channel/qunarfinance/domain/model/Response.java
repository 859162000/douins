package com.douins.agency.service.channel.qunarfinance.domain.model;

import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;

public class Response {
	
	private List<ApplyInfoItem> ApplyInfoList;
	@XStreamAlias("PolicyNo")
	private String policyNo;
	@XStreamAlias("ApplyNo")
	private String applyNo;
	@XStreamAlias("IDNO")
	private String idNo;
	@XStreamAlias("TotalPremium")
	private String totalPremium;
	@XStreamAlias("CurrentIncome")
	private String currentIncome;
	@XStreamAlias("Principle")
	private String principle;
	@XStreamAlias("TotalIncome")
	private String totalIncome;
	@XStreamAlias("InterestEffectiveDate")
	private String interestEffectiveDate;
	@XStreamAlias("PolicyEffectiveDate")
	private String policyEffectiveDate;
	@XStreamAlias("Comments")
	private String comments;
	@XStreamAlias("Error")
	private String error;
	
	
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public List<ApplyInfoItem> getApplyInfoList() {
		return ApplyInfoList;
	}
	public void setApplyInfoList(List<ApplyInfoItem> applyInfoList) {
		ApplyInfoList = applyInfoList;
	}
	public String getPolicyNo() {
		return policyNo;
	}
	public void setPolicyNo(String policyNo) {
		this.policyNo = policyNo;
	}
	public String getApplyNo() {
		return applyNo;
	}
	public void setApplyNo(String applyNo) {
		this.applyNo = applyNo;
	}
	public String getIdNo() {
		return idNo;
	}
	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}
	public String getTotalPremium() {
		return totalPremium;
	}
	public void setTotalPremium(String totalPremium) {
		this.totalPremium = totalPremium;
	}
	public String getPrinciple() {
		return principle;
	}
	public void setPrinciple(String principle) {
		this.principle = principle;
	}
	public String getCurrentIncome() {
		return currentIncome;
	}
	public void setCurrentIncome(String currentIncome) {
		this.currentIncome = currentIncome;
	}
	public String getTotalIncome() {
		return totalIncome;
	}
	public void setTotalIncome(String totalIncome) {
		this.totalIncome = totalIncome;
	}
	public String getInterestEffectiveDate() {
		return interestEffectiveDate;
	}
	public void setInterestEffectiveDate(String interestEffectiveDate) {
		this.interestEffectiveDate = interestEffectiveDate;
	}
	public String getPolicyEffectiveDate() {
		return policyEffectiveDate;
	}
	public void setPolicyEffectiveDate(String policyEffectiveDate) {
		this.policyEffectiveDate = policyEffectiveDate;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	@Override
	public String toString() {
		return "Response [ApplyInfoList=" + ApplyInfoList + ", policyNo=" + policyNo + ", applyNo=" + applyNo
				+ ", idNo=" + idNo + ", totalPremium=" + totalPremium + ", currentIncome=" + currentIncome
				+ ", principle=" + principle + ", totalIncome=" + totalIncome + ", interestEffectiveDate="
				+ interestEffectiveDate + ", policyEffectiveDate=" + policyEffectiveDate + ", comments=" + comments
				+ ", error=" + error + "]";
	}
	
	
	
	

	
	
}
