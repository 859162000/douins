package com.douins.insurance.domain.modelLianlife;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
public class Header {
	//response
	@XStreamAlias("Cost")
	//@XStreamOmitField
	private String  cost;
	@XStreamAlias("ResponseCode")
	private String  responseCode;
	private String  ackuuid;
	//request
	@XStreamAlias("Asyn")
	private String  asyn;
	@XStreamAlias("Code")
	private String  code;
	@XStreamAlias("PartnerIdentifier")
	private String  partneridentifier;
	@XStreamAlias("AsynReturnUrl")
	private String asynreturnurl;
	@XStreamAlias("PartnerSerial")
	private String partnerserial;
	//request //response
	@XStreamAlias("Time")
	private String  time;
	@XStreamAlias("UUID")
	private String  uuid;
	@XStreamAlias("AckUUID")
	private String  ackUUID;
	@XStreamAlias("ErrorMessage")
	private String  errorMessage;
	
	public String getPartneridentifier() {
		return partneridentifier;
	}
	public void setPartneridentifier(String partneridentifier) {
		this.partneridentifier = partneridentifier;
	}
	public String getAsynreturnurl() {
		return asynreturnurl;
	}
	public void setAsynreturnurl(String asynreturnurl) {
		this.asynreturnurl = asynreturnurl;
	}
	public String getPartnerserial() {
		return partnerserial;
	}
	public void setPartnerserial(String partnerserial) {
		this.partnerserial = partnerserial;
	}
	public String getCost() {
		return cost;
	}
	public void setCost(String cost) {
		this.cost = cost;
	}
	public String getResponseCode() {
		return responseCode;
	}
	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}
	public String getAckuuid() {
		return ackuuid;
	}
	public void setAckuuid(String ackuuid) {
		this.ackuuid = ackuuid;
	}
	public String getAsyn() {
		return asyn;
	}
	public void setAsyn(String asyn) {
		this.asyn = asyn;
	}
	public String getpartneridentifier() {
		return partneridentifier;
	}
	public void setpartneridentifier(String partneridentifier) {
		this.partneridentifier = partneridentifier;
	}

	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getuuid() {
		return uuid;
	}
	public void setuuid(String uuid) {
		this.uuid = uuid;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getAckUUID() {
		return ackUUID;
	}
	public void setAckUUID(String ackUUID) {
		this.ackUUID = ackUUID;
	}
	
}
