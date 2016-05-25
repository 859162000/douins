package com.douins.agency.service.douins.domain.model;

import java.util.Date;

import com.douins.agency.service.BaseModel;

public class InsureRequestHeader extends BaseModel{
	
	private String chnlNo;
	private String tranCode;
	private String documentId;
	private String profileRequest;
	private String function;
	private String from;
	private String to;
	private String orderNo;
	private String businessId;
	private String agencyNo;
	private String status;
	private Date createTime;
	
	public String getChnlNo() {
		return chnlNo;
	}
	public void setChnlNo(String chnlNo) {
		this.chnlNo = chnlNo;
	}
	public String getTranCode() {
		return tranCode;
	}
	public void setTranCode(String tranCode) {
		this.tranCode = tranCode;
	}
	public String getDocumentId() {
		return documentId;
	}
	public void setDocumentId(String documentId) {
		this.documentId = documentId;
	}
	public String getProfileRequest() {
		return profileRequest;
	}
	public void setProfileRequest(String profileRequest) {
		this.profileRequest = profileRequest;
	}

	public String getFunction() {
		return function;
	}
	public void setFunction(String function) {
		this.function = function;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
}
