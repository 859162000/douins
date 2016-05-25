package com.douins.agency.service.insurance.lianlife.domain.model;

import java.util.List;

public class ApplyInfo {

	private String ChannelType,
	ChannelNum,
	ChannelReginNum,
	AgentNum,
	ApplyType,
	ApplyDate,
	EffectiveDate,
	TotalPremium;
	private Applicant applicant;
	private List<ProductInfo> listProductInfo;
	private InsuredInfo insuredInfo; 

	public InsuredInfo getInsuredInfo() {
		return insuredInfo;
	}

	public void setInsuredInfo(InsuredInfo insuredInfo) {
		this.insuredInfo = insuredInfo;
	}

	public Applicant getApplicant() {
		return applicant;
	}

	public void setApplicant(Applicant applicant) {
		this.applicant = applicant;
	}

	public List<ProductInfo> getListProductInfo() {
		return listProductInfo;
	}

	public void setListProductInfo(List<ProductInfo> listProductInfo) {
		this.listProductInfo = listProductInfo;
	}

	public String getChannelType() {
		return ChannelType;
	}

	public void setChannelType(String channelType) {
		ChannelType = channelType;
	}

	public String getChannelNum() {
		return ChannelNum;
	}

	public void setChannelNum(String channelNum) {
		ChannelNum = channelNum;
	}

	public String getChannelReginNum() {
		return ChannelReginNum;
	}

	public void setChannelReginNum(String channelReginNum) {
		ChannelReginNum = channelReginNum;
	}

	public String getAgentNum() {
		return AgentNum;
	}

	public void setAgentNum(String agentNum) {
		AgentNum = agentNum;
	}

	public String getApplyType() {
		return ApplyType;
	}

	public void setApplyType(String applyType) {
		ApplyType = applyType;
	}

	public String getApplyDate() {
		return ApplyDate;
	}

	public void setApplyDate(String applyDate) {
		ApplyDate = applyDate;
	}

	public String getEffectiveDate() {
		return EffectiveDate;
	}

	public void setEffectiveDate(String effectiveDate) {
		EffectiveDate = effectiveDate;
	}

	public String getTotalPremium() {
		return TotalPremium;
	}

	public void setTotalPremium(String totalPremium) {
		TotalPremium = totalPremium;
	}
	
	
}
