package com.douins.policy.domain.vo;

import java.io.Serializable;
import java.util.Date;

import com.douins.bank.domain.vo.NYGateWayUrlH5;

public class PolicyResult implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3724821393051961272L;
	private boolean isSuccess;        // 操作是否成功
	private String resultCode;         //  返回的 responseCode
	private String msg;                    // 返回的 errorMsg
	private String sendCode;           // 投保单号
    private String policyCode;          // 保单号
    private Date finishTime;
    private String orderId;             // 订单号
    private String uwFlag;              // 核保标识位，1 表示成功 0 表示失败
    private String failReason;
    private String requestType;
    private Date sendTime;              // 报文返回时间
    private String policyUrl;           // 电子保单下载地址
    private String applyno;
    
	public String getApplyno() {
		return applyno;
	}
	public void setApplyno(String applyno) {
		this.applyno = applyno;
	}
	public Date getFinishTime() {
		return finishTime;
	}
	public void setFinishTime(Date finishTime) {
		this.finishTime = finishTime;
	}
	public boolean isSuccess() {
		return isSuccess;
	}
	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}
	public String getResultCode() {
		return resultCode;
	}
	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getSendCode() {
		return sendCode;
	}
	public void setSendCode(String sendCode) {
		this.sendCode = sendCode;
	}
	public String getPolicyCode() {
		return policyCode;
	}
	public void setPolicyCode(String policyCode) {
		this.policyCode = policyCode;
	}
    public String getOrderId() {
        return orderId;
    }
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
    public String getUwFlag() {
        return uwFlag;
    }
    public void setUwFlag(String uwFlag) {
        this.uwFlag = uwFlag;
    }
    public String getFailReason() {
        return failReason;
    }
    public void setFailReason(String failReason) {
        this.failReason = failReason;
    }
    public String getRequestType() {
        return requestType;
    }
    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }
    public Date getSendTime() {
        return sendTime;
    }
    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }
    public String getPolicyUrl() {
        return policyUrl;
    }
    public void setPolicyUrl(String policyUrl) {
        this.policyUrl = policyUrl;
    }
	@Override
	public String toString() {
		return "PolicyResult [isSuccess=" + isSuccess + ", resultCode=" + resultCode + ", msg=" + msg + ", sendCode="
				+ sendCode + ", policyCode=" + policyCode + ", finishTime=" + finishTime + ", orderId=" + orderId
				+ ", uwFlag=" + uwFlag + ", failReason=" + failReason + ", requestType=" + requestType + ", sendTime="
				+ sendTime + ", policyUrl=" + policyUrl + ", applyno=" + applyno + "]";
	}
	
    

}
