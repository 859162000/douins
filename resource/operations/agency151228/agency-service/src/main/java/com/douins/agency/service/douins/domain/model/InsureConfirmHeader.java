package com.douins.agency.service.douins.domain.model;

import com.douins.agency.service.BaseModel;

public class InsureConfirmHeader extends BaseModel {

	private long  headId;
	private String chnlNo;
	private String tranCode;
	private String documentId;
	private String profileRequest;
	private String function;
	private String from;
	private String to;
	private String status;
	
	public String getFunction() {
		return function;
	}
	public void setFunction(String function) {
		this.function = function;
	}
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public long getHeadId() {
		return headId;
	}
	public void setHeadId(long headId) {
		this.headId = headId;
	}

}
