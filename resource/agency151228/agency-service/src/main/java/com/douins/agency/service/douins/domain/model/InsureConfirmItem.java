package com.douins.agency.service.douins.domain.model;

import com.douins.agency.service.BaseModel;

public class InsureConfirmItem extends BaseModel {

	private long  headId;
	private String tradeNo;
	private String applySeq;
	private String exopenId;
	private String bankSeqNo;
	private String bankTradeDate;
	private String payType;
	private String orderNo;
	private String Payment;
	private String businessId;
	private String agencyNo;
	public String getTradeNo() {
		return tradeNo;
	}
	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}

	public long getHeadId() {
		return headId;
	}
	public void setHeadId(long headId) {
		this.headId = headId;
	}
	public String getApplySeq() {
		return applySeq;
	}
	public void setApplySeq(String applySeq) {
		this.applySeq = applySeq;
	}
	public String getExopenId() {
		return exopenId;
	}
	public void setExopenId(String exopenId) {
		this.exopenId = exopenId;
	}
	public String getBankSeqNo() {
		return bankSeqNo;
	}
	public void setBankSeqNo(String bankSeqNo) {
		this.bankSeqNo = bankSeqNo;
	}
	public String getBankTradeDate() {
		return bankTradeDate;
	}
	public void setBankTradeDate(String bankTradeDate) {
		this.bankTradeDate = bankTradeDate;
	}
	public String getPayType() {
		return payType;
	}
	public void setPayType(String payType) {
		this.payType = payType;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getPayment() {
		return Payment;
	}
	public void setPayment(String payment) {
		Payment = payment;
	}
	public String getBusinessId() {
		return businessId;
	}
	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}
	public String getAgencyNo() {
		return agencyNo;
	}
	public void setAgencyNo(String agencyNo) {
		this.agencyNo = agencyNo;
	}
	
	
}
