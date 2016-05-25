package com.douins.agency.service.channel.qunarfinance.domain.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("header")
public class Header {
	//response
	@XStreamAlias("cost")
	private String  cost;
	@XStreamAlias("responsecode")
	private String  responseCode;
	@XStreamAlias("ackuuid")
	private String  ackuuid;
	//request
	private String  asyn;
	private String  partneridentifier;
	private String asynreturnurl;
	private String partnerserial;
	//request //response
	@XStreamAlias("code")
	private String  code;
	@XStreamAlias("time")
	private String  time;
	@XStreamAlias("uuid")
	private String  uuid;
	private String errormessage;
	
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
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
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
	public String getErrormessage() {
		return errormessage;
	}
	public void setErrormessage(String errormessage) {
		this.errormessage = errormessage;
	}
	
	
}
