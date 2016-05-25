package com.douins.sms.domain.model;

import java.sql.Timestamp;

import com.mango.core.abstractclass.BaseModel;
import com.mango.orm.KeyGenerator;

public class SmsSend extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2194351513812219138L;
	private Long id;
	private String smsId;
	private String transType;
	private String smsTemplateId;
	private String smsContent;
	private String mobile;
	private String smsApply;
	private String status;
	private Timestamp smsTime;
	private String resultMsg;
	private String isvalid;
	private String opUser;
	private String checkCode;// 验证码

	public String getCheckCode() {
		return checkCode;
	}

	public void setCheckCode(String checkCode) {
		this.checkCode = checkCode;
	}

	public String getOpUser() {
		return opUser;
	}

	public void setOpUser(String opUser) {
		this.opUser = opUser;
	}

	public String getTransType() {
		return transType;
	}

	public void setTransType(String transType) {
		this.transType = transType;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSmsId() {
		return smsId;
	}

	public void setSmsId(String smsId) {
		this.smsId = smsId;
	}

	public String getSmsTemplateId() {
		return smsTemplateId;
	}

	public void setSmsTemplateId(String smsTemplateId) {
		this.smsTemplateId = smsTemplateId;
	}

	public String getSmsContent() {
		return smsContent;
	}

	public void setSmsContent(String smsContent) {
		this.smsContent = smsContent;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getSmsApply() {
		return smsApply;
	}

	public void setSmsApply(String smsApply) {
		this.smsApply = smsApply;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Timestamp getSmsTime() {
		return smsTime;
	}

	public void setSmsTime(Timestamp smsTime) {
		this.smsTime = smsTime;
	}

	public void initPrimaryKey() {
		// TODO Auto-generated method stub 初始化逻辑主键
		this.setSmsId(KeyGenerator.randomSeqNum());
	}

	public String getResultMsg() {
		return resultMsg;
	}

	public void setResultMsg(String resultMsg) {
		this.resultMsg = resultMsg;
	}

	/*
	 * public String getHashcode() { return hashcode; }
	 * 
	 * public void setHashcode(String hashcode) { this.hashcode = hashcode; }
	 */

	public String getIsvalid() {
		return isvalid;
	}

	public void setIsvalid(String isvalid) {
		this.isvalid = isvalid;
	}

	@Override
	public int hashCode() {
		final int index = 17;
		int hashCode = 1;
		hashCode = index * hashCode + (mobile == null ? 0 : mobile.hashCode());
		hashCode = index * hashCode
				+ (transType == null ? 0 : transType.hashCode());
		return hashCode;
	}

}
