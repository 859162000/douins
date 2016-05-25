package com.mango.fortune.insurance.pflife.vo;

import java.math.BigDecimal;

public class PolicyInfo {
	private String policyNo;
	private String proposalNo;
	private String cvaliDate;
	private Integer insuredNum;
	private BigDecimal sumPrem;
	private BigDecimal sumAmount;
	private BigDecimal refundPrem;
	private String autoAplIndi;
	private String chargePeriod;
	private String chargeYear;
	private String coveragePeriod;
	private Integer coverageYear;
	private String epolicyUrl;
	private String skuRiskcode;
	private String legalBene;
	private String IsSucess;
	private String failedReason;
	
	public String getPolicyNo() {
		return policyNo;
	}
	public void setPolicyNo(String policyNo) {
		this.policyNo = policyNo;
	}
	public String getProposalNo() {
		return proposalNo;
	}
	public void setProposalNo(String proposalNo) {
		this.proposalNo = proposalNo;
	}
	public String getCvaliDate() {
		return cvaliDate;
	}
	public void setCvaliDate(String cvaliDate) {
		this.cvaliDate = cvaliDate;
	}
	public Integer getInsuredNum() {
		return insuredNum;
	}
	public void setInsuredNum(Integer insuredNum) {
		this.insuredNum = insuredNum;
	}
	public BigDecimal getSumPrem() {
		return sumPrem;
	}
	public void setSumPrem(BigDecimal sumPrem) {
		this.sumPrem = sumPrem;
	}
	public BigDecimal getSumAmount() {
		return sumAmount;
	}
	public void setSumAmount(BigDecimal sumAmount) {
		this.sumAmount = sumAmount;
	}
	public BigDecimal getRefundPrem() {
		return refundPrem;
	}
	public void setRefundPrem(BigDecimal refundPrem) {
		this.refundPrem = refundPrem;
	}
	public String getAutoAplIndi() {
		return autoAplIndi;
	}
	public void setAutoAplIndi(String autoAplIndi) {
		this.autoAplIndi = autoAplIndi;
	}
	public String getChargePeriod() {
		return chargePeriod;
	}
	public void setChargePeriod(String chargePeriod) {
		this.chargePeriod = chargePeriod;
	}
	public String getChargeYear() {
		return chargeYear;
	}
	public void setChargeYear(String chargeYear) {
		this.chargeYear = chargeYear;
	}
	public String getCoveragePeriod() {
		return coveragePeriod;
	}
	public void setCoveragePeriod(String coveragePeriod) {
		this.coveragePeriod = coveragePeriod;
	}
	public Integer getCoverageYear() {
		return coverageYear;
	}
	public void setCoverageYear(Integer coverageYear) {
		this.coverageYear = coverageYear;
	}
	public String getEpolicyUrl() {
		return epolicyUrl;
	}
	public void setEpolicyUrl(String epolicyUrl) {
		this.epolicyUrl = epolicyUrl;
	}
	public String getSkuRiskcode() {
		return skuRiskcode;
	}
	public void setSkuRiskcode(String skuRiskcode) {
		this.skuRiskcode = skuRiskcode;
	}
	public String getLegalBene() {
		return legalBene;
	}
	public void setLegalBene(String legalBene) {
		this.legalBene = legalBene;
	}
	public String getIsSucess() {
		return IsSucess;
	}
	public void setIsSucess(String isSucess) {
		IsSucess = isSucess;
	}
	public String getFailedReason() {
		return failedReason;
	}
	public void setFailedReason(String failedReason) {
		this.failedReason = failedReason;
	}

}
