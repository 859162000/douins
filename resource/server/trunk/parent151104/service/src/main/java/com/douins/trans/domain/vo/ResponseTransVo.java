/**
 * Copyright (c) 2015 www.sinorfc.com All Rights Reserved.  
 */
package com.douins.trans.domain.vo;

import java.io.Serializable;
import java.util.List;

import org.apache.poi.ss.formula.functions.T;

import com.douins.trans.domain.model.BusinessTrans;
import com.douins.trans.domain.model.ResponseTrans;

/**
 * @author xuhuiwang
 * @version 1.0, 2015年4月29日 上午8:40:47
 */
public class ResponseTransVo<T> implements Serializable {
	
	private static final long serialVersionUID = -6435165792086530521L;
	public ResponseTrans responseTrans;
	

	// Added by G.F. 2015-11-19 **** token
	private String accessToken;
	// 使用范型参数，避免每次都要封装一个类型的Vo
	private  T responseParams;
	public ResponseTrans getResponseTrans() {
		return responseTrans;
	}
	public void setResponseTrans(ResponseTrans responseTrans) {
		this.responseTrans = responseTrans;
	}
	
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public T getResponseParams() {
		return responseParams;
	}
	public void setResponseParams(T responseParams) {
		this.responseParams = responseParams;
	}
	
	
}
