package com.douins.agency.service.channel.qunarfinance.domain.vo;

public class ResponseVo {
	private String responsecode;
	private String uuid;
	private String ackuuid;
	private String code;
	private String time;
	
	public String getResponsecode() {
		return responsecode;
	}
	public void setResponsecode(String responsecode) {
		this.responsecode = responsecode;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
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
	
	public String toString(){
		return "responsecode="+responsecode+",uuid="+uuid+",code="+code+",time="+time;
	}
	
	
}
