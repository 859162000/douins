package com.douins.insurance.domain.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;

public class Header {
    @XStreamAlias("RequestType")
	private String transType;
    @XStreamAlias("From")
	private String submitChannel;
	private String orderId;
	private String applyDate;
	private String transDate;
	private String transTime;
	private String serialNo;
	private String asyn;
	private String returnUrl;
	private String responseCode;
	private String errorMessage;
	@XStreamAlias("PostCode")
	private String postCode;       // 营销网点代码
	@XStreamAlias("AgentCode")
	private String agentCode;          // 代理人编码
	@XStreamAlias("AgentChannel")
	private String agentChannel = "2";       // 代理人渠道，固定为2
	@XStreamAlias("SendTime")
	private String sendTime;              // 报文发送时间
	@XStreamAlias("IsPackage")
	private String isPackage;          // 是否为打包产品
	
	public String getTransType() {
		return transType;
	}
	public void setTransType(String transType) {
		this.transType = transType;
	}
	public String getSubmitChannel() {
		return submitChannel;
	}
	public void setSubmitChannel(String submitChannel) {
		this.submitChannel = submitChannel;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getApplyDate() {
		return applyDate;
	}
	public void setApplyDate(String applyDate) {
		this.applyDate = applyDate;
	}
	public String getTransTime() {
		return transTime;
	}
	public void setTransTime(String transTime) {
		this.transTime = transTime;
	}
	public String getSerialNo() {
		return serialNo;
	}
	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}
	public String getAsyn() {
		return asyn;
	}
	public void setAsyn(String asyn) {
		this.asyn = asyn;
	}
	public String getReturnUrl() {
		return returnUrl;
	}
	public void setReturnUrl(String returnUrl) {
		this.returnUrl = returnUrl;
	}
	public String getResponseCode() {
		return responseCode;
	}
	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public String getTransDate() {
		return transDate;
	}
	public void setTransDate(String transDate) {
		this.transDate = transDate;
	}
    public String getPostCode() {
        return postCode;
    }
    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }
    public String getAgentCode() {
        return agentCode;
    }
    public void setAgentCode(String agentCode) {
        this.agentCode = agentCode;
    }
    public String getAgentChannel() {
        return agentChannel;
    }
    public void setAgentChannel(String agentChannel) {
        this.agentChannel = agentChannel;
    }
    public String getSendTime() {
        return sendTime;
    }
    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }
    public String getIsPackage() {
        return isPackage;
    }
    public void setIsPackage(String isPackage) {
        this.isPackage = isPackage;
    }

}
