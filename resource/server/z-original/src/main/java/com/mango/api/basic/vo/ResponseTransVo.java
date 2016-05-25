/**
 * Copyright (c) 2015 www.sinorfc.com All Rights Reserved.  
 */
package com.mango.api.basic.vo;

import java.io.Serializable;

import com.mango.fortune.trans.model.ResponseTrans;

/**
 * @author xuhuiwang
 * @version 1.0, 2015年4月29日 上午8:40:47
 */
public class ResponseTransVo implements Serializable {
	
	private static final long serialVersionUID = -6435165792086530521L;
	public ResponseTrans responseTrans;

	public ResponseTrans getResponseTrans() {
		return responseTrans;
	}

	public void setResponseTrans(ResponseTrans responseTrans) {
		this.responseTrans = responseTrans;
	}
}
