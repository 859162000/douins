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
public class RequestBusinessTransVo<T> implements Serializable {
	
	private static final long serialVersionUID = 5331431548639015547L;
	private TransRequest requestParms;
	
	private String accessToken;
	private T requestParams;

	
    public TransRequest getRequestParms() {
		return requestParms;
	}

	public void setRequestParms(TransRequest requestParms) {
		this.requestParms = requestParms;
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
