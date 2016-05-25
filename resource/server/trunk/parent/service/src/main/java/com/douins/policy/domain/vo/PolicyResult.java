package com.douins.policy.domain.vo;

import java.io.Serializable;
import java.util.Date;

public class PolicyResult implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3724821393051961272L;
	private boolean isSuccess;
	private String resultCode;
	private String msg;
	private String sendCode;
    private String policyCode;
    private Date finishTime;
    
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

}
