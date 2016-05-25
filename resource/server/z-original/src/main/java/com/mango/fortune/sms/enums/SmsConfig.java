package com.mango.fortune.sms.enums;

public enum SmsConfig {
	ISTEST("调试短信："), 
	//YUNPIANAPIKEY("6c852b6f69a97518c8499ab7173b145a")
	YUNPIANAPIKEY("f24441bcc558f1f69151cf1f6e393095"),
	CHANZORACCOUNT("douyajinfu"),
	CHANZORPASSWORD("152726"),
	CHANZORURL("http://sms.chanzor.com:8001/sms.aspx?action=send")
	;
	private String code;

	private SmsConfig(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
