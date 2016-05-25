package com.douins.agency.service.douins.domain.model;

import com.douins.agency.service.BaseModel;

public class LianlifeReqHeader  extends BaseModel{
	
	 private String asyn,code,partnerIdentifier,sendTime,uuid;

	public String getAsyn() {
		return asyn;
	}

	public void setAsyn(String asyn) {
		this.asyn = asyn;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getPartnerIdentifier() {
		return partnerIdentifier;
	}

	public void setPartnerIdentifier(String partnerIdentifier) {
		this.partnerIdentifier = partnerIdentifier;
	}

	public String getSendTime() {
		return sendTime;
	}

	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

}
