package com.douins.insurance.domain.model;

import java.util.List;

import com.douins.insurance.domain.vo.BenifitVo;
import com.douins.insurance.domain.vo.HolderVo;
import com.thoughtworks.xstream.annotations.XStreamAlias;

public class Request {
    @XStreamAlias("Order")
    private Policy policy;
    @XStreamAlias("ApplyInfo")
    private HolderVo holder;
    @XStreamAlias("BenefitInfo")
    private BenifitVo benifit;
    
    public Policy getPolicy() {
        return policy;
    }
    public void setPolicy(Policy policy) {
        this.policy = policy;
    }
    public HolderVo getHolder() {
        return holder;
    }
    public void setHolder(HolderVo holder) {
        this.holder = holder;
    }
    public BenifitVo getBenifit() {
        return benifit;
    }
    public void setBenifit(BenifitVo benifit) {
        this.benifit = benifit;
    }
    
    
    // Added by G.F. 2015-12-15
//	private PolicyInfo policyInfo;
//	private HoderInfo hoderInfo;
//	private List<InsuredInfo> insuredInfoList;
//	private List<BenifitInfo> benifitInfoList;
//	private List<RiskInfo> riskInfoList;
//	private PayInfo payInfo;
//	
//	public PolicyInfo getPolicyInfo() {
//		return policyInfo;
//	}
//	public void setPolicyInfo(PolicyInfo policyInfo) {
//		this.policyInfo = policyInfo;
//	}
//	public HoderInfo getHoderInfo() {
//		return hoderInfo;
//	}
//	public void setHoderInfo(HoderInfo hoderInfo) {
//		this.hoderInfo = hoderInfo;
//	}
//	public List<InsuredInfo> getInsuredInfoList() {
//		return insuredInfoList;
//	}
//	public void setInsuredInfoList(List<InsuredInfo> insuredInfoList) {
//		this.insuredInfoList = insuredInfoList;
//	}
//	public List<BenifitInfo> getBenifitInfoList() {
//		return benifitInfoList;
//	}
//	public void setBenifitInfoList(List<BenifitInfo> benifitInfoList) {
//		this.benifitInfoList = benifitInfoList;
//	}
//	public List<RiskInfo> getRiskInfoList() {
//		return riskInfoList;
//	}
//	public void setRiskInfoList(List<RiskInfo> riskInfoList) {
//		this.riskInfoList = riskInfoList;
//	}
//	public PayInfo getPayInfo() {
//		return payInfo;
//	}
//	public void setPayInfo(PayInfo payInfo) {
//		this.payInfo = payInfo;
//	}
}
