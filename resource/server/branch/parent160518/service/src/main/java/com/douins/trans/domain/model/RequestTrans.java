/**
 * Copyright (c) 2015 www.sinorfc.com All Rights Reserved.  
 */
package com.douins.trans.domain.model;

import java.util.Date;

import com.mango.core.abstractclass.AbstractModel;
import com.mango.orm.KeyGenerator;
/**
 * @author xuhuiwang
 * @version 1.0, 2015年4月22日 下午1:00:48   
 */
public class RequestTrans extends AbstractModel{

	private static final long serialVersionUID = -6826753821630338682L;
	private Long id;
	private String requestTransId;
	private String transNo;
	private String transId;
	private String transType;
	private String transChannel;
	private Date transTimeDate;
	private String transTime;
	private Date requestTime;
	private String ipAddress;
	private String clientType;
	private String responseCode;
	private String responseMsg;
	private String flowNo;//银行交易流水账号。
	/* (non-Javadoc)
	 * @see com.mango.core.abstractclass.AbstractModel#initPrimaryKey()
	 */
	@Override
	public void initPrimaryKey() {
		this.setRequestTransId(KeyGenerator.randomSeqNum());
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getRequestTransId() {
		return requestTransId;
	}

	public void setRequestTransId(String requestTransId) {
		this.requestTransId = requestTransId;
	}

	public String getTransType() {
		return transType;
	}
	public void setTransType(String transType) {
		this.transType = transType;
	}
	public String getTransChannel() {
		return transChannel;
	}
	public void setTransChannel(String transChannel) {
		this.transChannel = transChannel;
	}
	
	public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	public String getClientType() {
		return clientType;
	}
	public void setClientType(String clientType) {
		this.clientType = clientType;
	}

	public String getTransNo() {
		return transNo;
	}

	public void setTransNo(String transNo) {
		this.transNo = transNo;
	}

	public String getTransId() {
		return transId;
	}

	public void setTransId(String transId) {
		this.transId = transId;
	}

	public Date getTransTimeDate() {
		return transTimeDate;
	}

	public void setTransTimeDate(Date transTimeDate) {
		this.transTimeDate = transTimeDate;
	}

	public String getTransTime() {
		return transTime;
	}

	public void setTransTime(String transTime) {
		this.transTime = transTime;
	}

	public Date getRequestTime() {
		return requestTime;
	}

	public void setRequestTime(Date requestTime) {
		this.requestTime = requestTime;
	}

	public String getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

	public String getResponseMsg() {
		return responseMsg;
	}

	public void setResponseMsg(String responseMsg) {
		this.responseMsg = responseMsg;
	}

	public String getFlowNo() {
		return flowNo;
	}

	public void setFlowNo(String flowNo) {
		this.flowNo = flowNo;
	}

	
	
	
	
}
