package com.mango.fortune.sms.chanzor;

public class ChanzorSendData {

	private String userid = "";
	private String account = "";
	private String password = "";
	private String mobile = "";
	private String sendTime = "";
	private String content = "";

	public ChanzorSendData() {

	}

	public ChanzorSendData(String account, String password, String mobile,
			String content) {
		this.account = account;
		this.password = password;
		this.mobile = mobile;
		this.content = content;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getSendTime() {
		return sendTime;
	}

	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}
}
