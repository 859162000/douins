package com.douins.policy.domain.model;

public class PolicyData {

	private String policyNo,applyNo,idNo,
	totalPremium,principle,currentIncome,
	totalIncome,
	cashValue,wdPoundage,wdPremium,
	interestEffectiveDate,policyEffectiveDate,comments,createTime,refundDate;

	
	public String getCashValue() {
		return cashValue;
	}

	public void setCashValue(String cashValue) {
		this.cashValue = cashValue;
	}

	public String getWdPoundage() {
		return wdPoundage;
	}

	public void setWdPoundage(String wdPoundage) {
		this.wdPoundage = wdPoundage;
	}

	public String getWdPremium() {
		return wdPremium;
	}

	public void setWdPremium(String wdPremium) {
		this.wdPremium = wdPremium;
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

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getRefundDate() {
		return refundDate;
	}

	public void setRefundDate(String refundDate) {
		this.refundDate = refundDate;
	}
	
}