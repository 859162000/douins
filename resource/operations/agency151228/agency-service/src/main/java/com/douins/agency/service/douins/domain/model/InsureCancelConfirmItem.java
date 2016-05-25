package com.douins.agency.service.douins.domain.model;

import com.douins.agency.service.BaseModel;

public class InsureCancelConfirmItem extends BaseModel{
  private String headId;
  private String policyNo;
  private String tradeNo;
  
	public String getHeadId() {
		return headId;
	}
	public void setHeadId(String headId) {
		this.headId = headId;
	}
	public String getPolicyNo() {
		return policyNo;
	}
	public void setPolicyNo(String policyNo) {
		this.policyNo = policyNo;
	}
	public String getTradeNo() {
		return tradeNo;
	}
	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}
	  
}
