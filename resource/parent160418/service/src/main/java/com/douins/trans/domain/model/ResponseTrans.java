/**
 * Copyright (c) 2015 www.sinorfc.com All Rights Reserved.  
 */
package com.douins.trans.domain.model;

import java.io.Serializable;

import com.mango.core.common.util.DateUtils;

/**
 * @author xuhuiwang
 * @version 1.0, 2015年4月23日 下午3:23:03   
 */
public class ResponseTrans implements Serializable{
	
	private static final long serialVersionUID = 4118177806889502511L;
	private String responseCode;
	private String msg;
	private String responseTime;
	private String transId;
	public ResponseTrans (){
		this.responseTime = DateUtils.currentDatetime();
	}
	
	public ResponseTrans(String responseCode,String msg,String transId){
		this.responseCode= responseCode;
		this.msg = msg;
		this.transId = transId;
		this.responseTime = DateUtils.currentDatetime();
	}
	
	public ResponseTrans(String responseCode,String msg){
		this.responseCode= responseCode;
		this.msg = msg;
		this.responseTime = DateUtils.currentDatetime();
	}

	public String getResponseCode() {
		return responseCode;
	}
	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getResponseTime() {
		return responseTime;
	}
	public void setResponseTime(String responseTime) {
		this.responseTime = responseTime;
	}
	public String getTransId() {
		return transId;
	}
	public void setTransId(String transId) {
		this.transId = transId;
	}
	
	
	
}
