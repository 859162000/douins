package com.douins.agency.service.channel.qunarfinance.domain.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;

public class HeaderWdQuery {
	//response
	@XStreamAlias("Cost")
	private String  cost;
	@XStreamAlias("ResponseCode")
	private String  responseCode;
	@XStreamAlias("AckUUID")
	private String  ackuuid;
	@XStreamAlias("Code")
	private String  code;
	@XStreamAlias("Time")
	private String  time;
	@XStreamAlias("UUID")
	private String  uuid;
	@XStreamAlias("Asyn")
	private String  asyn;
	
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
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	
	public String getAsyn() {
		return asyn;
	}
	public void setAsyn(String asyn) {
		this.asyn = asyn;
	}
	
}
