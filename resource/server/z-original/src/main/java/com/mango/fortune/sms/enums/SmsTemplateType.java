package com.mango.fortune.sms.enums;

public enum SmsTemplateType {
	/**
	 * 注册短信验证码
	 */
	REGISTCHECKCODE("2", "您的验证码是#code##isTest#【#company#】" ,"chanzorSmsService","101"),
	RESETPASSWORD("2", "您的验证码是#code##isTest#【#company#】" ,"chanzorSmsService","103"),//忘记登录密码
	RESETPAYPASSWORD("2", "您的验证码是#code##isTest#【#company#】" ,"chanzorSmsService","105")//忘记交易密码
	
	;
	
	
	private String value;
	private String content;
	private String apply;
	
	private String type;//验证码类型
	
	private SmsTemplateType(String value, String content,String apply,String type) {
		this.value = value;
		this.content = content;
		this.apply = apply;
		this.type =type;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getApply() {
		return apply;
	}

	public void setApply(String apply) {
		this.apply = apply;
	}
	

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public static String getContentByValue(String value) {
		for (SmsTemplateType type : SmsTemplateType.values()) {
			if (type.getValue().equals(value))
				return type.getContent();
		}
		return "";
	}
	public static SmsTemplateType getSmsTemplateTypebyType(String typevalue){
		for (SmsTemplateType type : SmsTemplateType.values()) {
			if (type.getType().equals(typevalue))
				return type;
		}
		return null;
	}
	public static String getApplyByValue(String value) {
		for (SmsTemplateType type : SmsTemplateType.values()) {
			if (type.getValue().equals(value))
				return type.getApply();
		}
		return "";
	}
}
