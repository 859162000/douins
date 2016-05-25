package com.mango.api.basic.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;

public class MobileMsg implements Serializable{
	
	
	/**
	 * MobileMsg.java 
	 */
	private static final long serialVersionUID = 1L;
	public static HashMap<String,MobileMsg> msgMap = new HashMap<String,MobileMsg>();
	private String mobile;
	private String transType;
	private String msg;
	private Date date;
	public MobileMsg(){
		this.date= new Date();
	} 
	public MobileMsg(String mobile,String transType,String msg){
		this.mobile= mobile;
		this.transType= transType;
		this.msg= msg;
		this.date= new Date();
	}

	@Override
	public int hashCode() {
		final int index = 17;
		int hashCode = 1;
		hashCode = index * hashCode + (mobile == null ? 0 : mobile.hashCode());
		hashCode = index * hashCode + (transType == null ? 0 : transType.hashCode());
		return hashCode;
	}

	public static int calHashCode(String transType,String mobile){
		final int index = 17;
		int hashCode = 1;
		hashCode = index * hashCode + (mobile == null ? 0 : mobile.hashCode());
		hashCode = index * hashCode + (transType == null ? 0 : transType.hashCode());
		return hashCode;
	}
	public static boolean check(String transType,String mobile,String verificationCode){
		
		String key = String.valueOf(MobileMsg.calHashCode(transType, mobile));				
		MobileMsg mobileMsg = MobileMsg.msgMap.get(key);
		if(mobileMsg.getMsg().equals(verificationCode)){
			return true;
		}else{
			return false;
		}
	}
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getTransType() {
		return transType;
	}

	public void setTransType(String transType) {
		this.transType = transType;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	
}
