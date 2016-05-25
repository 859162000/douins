/**
 * Copyright (c) 2015 www.sinorfc.com All Rights Reserved.  
 */
package com.douins.trans.domain.vo;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.douins.trans.domain.model.RequestTrans;



/**
 * @author xuhuiwang
 * @version 1.0, 2015年4月28日 下午4:25:03
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class RequestTransVo<T> implements Serializable {
	
	private RequestTrans requestTrans;
	private String accessToken;
	private T requestParams;

	public RequestTrans getRequestTrans() {
		return requestTrans;
	}

	public void setRequestTrans(RequestTrans requestTrans) {
		this.requestTrans = requestTrans;
	}

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public T getRequestParams() {
        return requestParams;
    }

    public void setRequestParams(T requestParams) {
        this.requestParams = requestParams;
    }
}
