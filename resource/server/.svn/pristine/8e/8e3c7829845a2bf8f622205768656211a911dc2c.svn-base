package com.mango.fortune.sms.enums;

public enum SmsStatusType {

	NOT_SEND("0", "未发送"), 
	HAVE_SEND_SUCCESS("1", "已发送成功"), 
	HAVE_SEND_FAIL("2","发送失败");

	private String code;
	private String name;

	private SmsStatusType(String code, String name) {
		this.code = code;
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static String getValueByName(String name) {
		for (SmsStatusType type : SmsStatusType.values()) {
			if (type.getName().equals(name))
				return type.getCode();
		}
		return "";
	}

	public static String getNameByValue(String code) {
		for (SmsStatusType type : SmsStatusType.values()) {
			if (type.getCode().equals(code))
				return type.getName();
		}
		return "";
	}

}
